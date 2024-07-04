import dash_bootstrap_components as dbc
import plotly.io as pio
import pandas as pd
import plotly.figure_factory as ff
import matplotlib.cm as cm
from plotly.express import line
from datetime import datetime, timedelta


def make_report(scheduling, availability_history):
    data = parse_scheduling_json(scheduling)
    fig_sched = make_gantt_chart(data)
    fig_ongoing = make_maints_ongoing_plot(data, list(scheduling.keys()))
    fig_availability = make_availability_plot(availability_history, list(scheduling.keys()))

    html = lambda fig: fig.to_html(full_html=False, default_height="90vh")

    return '''
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="'''+ dbc.themes.DARKLY +'''">
        <style> .section { display: none; } </style>
    </head>
    <body>
      <script>
        function show(id) {
            for (let element of document.getElementsByClassName('section')) {
                element.style.display = 'none';
            }
            document.getElementById(id).style.display = 'block';
            window.dispatchEvent(new Event('resize'));
        }
    </script>

    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
        <a class="navbar-brand" href="#">Scheduling report</a>
        <div class="navba" id="navbarColor01">
            <ul class="navbar-nav me-auto">
            <li class="nav-item">
                <button class="nav-link" onclick="show('schedule')">Schedule</a>
            </li>
            <li class="nav-item">
                <button class="nav-link" onclick="show('maintenances')">Maintenances</a>
            </li>
            <li class="nav-item">
                <button class="nav-link" onclick="show('vehicles')">Vehicles</a>
            </li>
            </ul>
        </div>
        </div>
    </nav>
        <div id="schedule" class="section" style="display: block">'''+ html(fig_sched) +'''</div>
        <div id="vehicles" class="section">'''+ html(fig_availability) +'''</div>
        <div id="maintenances" class="section">'''+ html(fig_ongoing) +'''</div>
    </body>
    </html>
    '''

def html(fig):
    return

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
    fig.update_layout(title='Fleet Maintenance Schedule', height=height)

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