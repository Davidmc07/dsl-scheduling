import dash_bootstrap_components as dbc
import plotly.io as pio
import pandas as pd
import plotly.figure_factory as ff
import matplotlib.cm as cm
from plotly.express import line
from datetime import datetime, timedelta
import json


def make_report(scheduling, availability_history):
    data = parse_scheduling_json(scheduling)
    fig_sched = make_gantt_chart(data)
    fig_ongoing = make_maints_ongoing_plot(data, list(scheduling.keys()))
    fig_availability = make_availability_plot(availability_history, list(scheduling.keys()))

    fig_sched.update_layout(template="plotly_dark")
    fig_ongoing.update_layout(template="plotly_dark")
    fig_availability.update_layout(template="plotly_dark")

    colors_dark, colors_light = _generate_color_maps(fig_sched)

    html = lambda fig: fig.to_html(full_html=False, default_height="90vh")

    return '''
    <!DOCTYPE html>
    <html data-bs-theme="dark">
    <head>
        <meta charset="utf-8" />
        <link id="bootstrap-theme" rel="stylesheet" href="'''+ dbc.themes.LUX +'''">
        <style> 
            .section { display: none; }
            .selector-rect {fill: #506784 !important;}
            .theme-toggle-btn { cursor: pointer; font-size: 1.2rem; }
            #main-navbar {max-height: 10vh}
        </style>
    </head>
    <body>
      <script>
        const GANTT_COLORS_DARK = '''+ json.dumps(colors_dark) +''';
        const GANTT_COLORS_LIGHT = '''+ json.dumps(colors_light) +''';

        function show(id) {
            for (let element of document.getElementsByClassName('section')) {
                element.style.display = 'none';
            }
            document.getElementById(id).style.display = 'block';
            window.dispatchEvent(new Event('resize'));
        }
        function toggleTheme() {
            const isDark = document.documentElement.getAttribute("data-bs-theme") === "dark";
            const nextTheme = isDark ? "light" : "dark";
            const btnIcon = isDark ? "🌙" : "☀️";
            document.documentElement.setAttribute("data-bs-theme", nextTheme);
            const navbar = document.getElementById('main-navbar');
            if (navbar) {
                navbar.setAttribute("data-bs-theme", nextTheme);
            }
            const themeBtn = document.getElementById('theme-btn');
            if (themeBtn) themeBtn.innerText = btnIcon;
            const cambiosLayout = nextTheme === "dark"
                ? '''+ _get_layout_changes_for_template("plotly_dark") +'''
                : '''+ _get_layout_changes_for_template("plotly") +''';

            const newGanttColors = nextTheme === "dark" ? GANTT_COLORS_DARK : GANTT_COLORS_LIGHT;

            document.querySelectorAll('.plotly-graph-div').forEach(div => {
                const plotlyLib = window.Plotly || (typeof Plotly !== 'undefined' ? Plotly : null);
                if (plotlyLib && div.id) {
                    plotlyLib.relayout(div.id, cambiosLayout);

                    const parent = div.closest('.section');
                    if (parent && parent.id.includes('schedule')) {
                        Plotly.restyle(div, {
                            fillcolor: newGanttColors,
                            "hoverlabel.bgcolor": newGanttColors
                        });
                    }
                }
            });
        }
    </script>
    <nav id="main-navbar" class="navbar navbar-expand-lg" data-bs-theme="dark">
        <div class="container-fluid">
        <a class="navbar-brand" href="#">Scheduling report</a>
        <div class="navbar-collapse d-flex justify-content-between" id="navbarColor01">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 flex-row gap-3">
            <li class="nav-item">
                <button class="nav-link" onclick="show('schedule')">Schedule</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" onclick="show('maintenances')">Maintenances</button>
            </li>
            <li class="nav-item">
                <button class="nav-link" onclick="show('vehicles')">Vehicles</button>
            </li>
            </ul>
            <button id="theme-btn" class="btn theme-toggle-btn" onclick="toggleTheme()">☀️</button>
        </div>
        </div>
    </nav>
        <div id="schedule" class="section" style="display: block">'''+ html(fig_sched) +'''</div>
        <div id="vehicles" class="section">'''+ html(fig_availability) +'''</div>
        <div id="maintenances" class="section">'''+ html(fig_ongoing) +'''</div>
    </body>
    </html>
    '''

def _generate_color_maps(fig):
    to_rgba_str = lambda r,g,b,a: f"rgba({int(r*255)},{int(g*255)},{int(b*255)},{a})"
    colors_dark = []
    colors_light = []

    for trace in fig.data:
        try:
            d = float(trace.name)
            colors_dark.append(to_rgba_str(*cm.viridis(d)))
            colors_light.append(to_rgba_str(*cm.viridis_r(d)))
        except:
            continue

    return colors_dark, colors_light


def parse_scheduling_json(scheduling):
    data = {
        'Task': [],
        'Start': [],
        'Finish': [],
        'Duration': [],
        'Installation': []
    }
    start = 0
    for period, maintenances in scheduling.items():
        if not maintenances: continue

        start = datetime.strptime(period, "%Y-%m-%d")
        for maint in maintenances:
            data['Task'].append(maint['ID'])
            data['Start'].append(start)
            data['Finish'].append(start + timedelta(days=int(maint['Duration (days)'])))
            data['Duration'].append(int(maint['Duration (days)']))
            data['Installation'].append(maint['Installation'])

    df = pd.DataFrame(data).sort_values('Task', ascending=True)
    df['Duration'] = df['Duration'] / df['Duration'].max()
    return df


def make_gantt_chart(df):
    pio.templates.default = "plotly_dark"
    colors = {duration: cm.viridis(duration) for duration in df['Duration']}
    
    if (len(df) <= 0): return no_maints_gantt(df)

    fig = ff.create_gantt(
        df, 
        index_col='Duration', 
        group_tasks=True, 
        colors=colors, 
        showgrid_x=True, 
        showgrid_y=True
    )
    #fig.layout.xaxis.type = 'linear'
    height = 250 + len(set(df['Task'])) * 30
    fig.update_yaxes(categoryorder='total ascending', title_text='Vehicles')
    fig.update_xaxes(title_text='Time')
    fig.update_layout(
        title='Fleet Maintenance Schedule', 
        height=height,
        xaxis=dict(
            automargin=True,
            rangeselector=dict(
                buttons=[dict(step="all")],
                y=1.0, yanchor='bottom'
            )
        )
    )

    return fig

def no_maints_gantt(df):
    fig = ff.create_gantt(
        df, 
        showgrid_x=True, 
        showgrid_y=True
    )
    #fig.layout.xaxis.type = 'linear'
    fig.update_layout(title='Fleet Maintenance Schedule')
    fig.add_annotation(
        text="No maintenances scheduled",
        showarrow=False,
        font=dict(size=14),
        align="center",
    )
    return fig


def make_availability_plot(availability, periods):
    periods.append(get_date_with_diff(periods[-1],1))
    fig = line(x=periods, y=availability)
    fig.update_yaxes(title_text="Available vehicles")
    fig.update_xaxes(title_text="Time")
    fig.update_layout(title='Vehicle availability', showlegend=False)
    return fig

def make_maints_ongoing_plot(df, periods):
    maints = []
    if len(periods) == 1:
        periods.append(get_date_with_diff(periods[0],1))

    if df.empty: 
        maints.extend([0 for _ in range(len(periods))])
    else:
        for i in periods:
            maints.append(sum((df['Start'] <= i) & (df['Finish'] > i)))
    
    fig = line(x=periods, y=maints)
    fig.update_yaxes(title_text="Ongoing maintenances")
    fig.update_xaxes(title_text="Time")
    fig.update_layout(title='Ongoing maintenances per period', showlegend=False)
    return fig

def get_date_with_diff(date_str, days_diff):
    date = datetime.strptime(date_str, "%Y-%m-%d")
    diff = timedelta(days=days_diff)
    return (date + diff).strftime("%Y-%m-%d")

def _get_layout_changes_for_template(template):
    template_dict = pio.templates[template].layout.to_plotly_json()
    clean_template = {
        "colorway": template_dict["colorway"],
        "font": template_dict["font"],
        "paper_bgcolor": template_dict["paper_bgcolor"],
        "plot_bgcolor": template_dict["plot_bgcolor"],
        "hoverlabel": template_dict["hoverlabel"],

        "xaxis": {
            "gridcolor": template_dict["xaxis"]["gridcolor"],
            "linecolor": template_dict["xaxis"]["linecolor"],
            "showline": False,
            "rangeselector.font.color": "#ffffff"
        },
        "yaxis": {
            "gridcolor": template_dict["yaxis"]["gridcolor"],
            "linecolor": template_dict["yaxis"]["linecolor"],
            "showline": False
        }
    }
    return json.dumps(_flatten_dict(clean_template))

    
def _flatten_dict(d, parent_key=""):
    items = {}

    for k, v in d.items():
        new_key = f"{parent_key}.{k}" if parent_key else k

        if isinstance(v, dict):
            items.update(_flatten_dict(v, new_key))
        else:
            items[new_key] = v

    return items