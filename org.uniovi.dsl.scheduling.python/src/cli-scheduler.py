from dependencies import *
import argparse, json
from datetime import datetime
import warnings; 
warnings.filterwarnings('ignore')
import sys; sys.stdout.reconfigure(encoding='utf-8')


class ParseMaintAction(argparse.Action):
    def __call__(self, parser, namespace, values, option_string=None):
        globals()["maintenances"] = self.create_objects(values, namespace)

    def create_objects(self, values, namespace):
        data = list(map(lambda x: json.loads(x), values))
        res = []

        for value in data: # Handle references to objects not created yet
            globals()[value['id']] = Maintenance(None, None, None, None)

        for value in data:
            maint = globals()[value['id']]
            del value['id']
            maint.__dict__.update(value)
            maint.includes = list(map(lambda x: globals()[x], maint.includes))
            maint.places = list(map(lambda x: globals()[x], maint.places))
            res.append(maint)

        return res
    
class ParseInstallationAction(argparse.Action):
    def __call__(self, parser, namespace, values, option_string=None):
        globals()["installations"] = self.create_objects(values, namespace)

    def create_objects(self, values, namespace):
        data = list(map(lambda x: json.loads(x), values))
        res = []

        for value in data: # Handle references to objects not created yet
            place = MaintenancePlace(value['name'], value['capacity'])
            globals()[value['id']] = place
            res.append(place)

        return res

def date_type(date_str: str) -> datetime:
    try: 
        if '/' in date_str:
            return datetime.strptime(date_str, "%d/%m/%Y")
        else:
            return datetime.strptime(date_str, "%d-%m-%Y")
    except ValueError: 
        raise argparse.ArgumentTypeError(f"Not a valid date: {date_str!r}")
    
parser = argparse.ArgumentParser()
parser.add_argument('--path', required=False, type=str, default=None)
parser.add_argument('--dark-mode', action="store_true")

parser.add_argument('--dsl-filename', required=False, type=str, default="Unnamed file")
parser.add_argument('--installations', nargs='+', action=ParseInstallationAction, required=True)
parser.add_argument('--maintenances', nargs='+', action=ParseMaintAction, required=True)
#Options
parser.add_argument('--input', required=True, type=str)
parser.add_argument('--output', required=True, type=str)
parser.add_argument('--days-per-period', required=False, type=int, default=1)
parser.add_argument('--number-of-periods', required=False, type=int, default=100)
parser.add_argument('--random-state', required=False, type=int, default=42)
parser.add_argument('--extra-hours', required=False, type=int, default=0)
parser.add_argument('--prob-before-scheduling', required=False, type=float, default=1)
parser.add_argument('--rate-of-change', required=False, type=float, default=5)
parser.add_argument('--max-iters', required=False, type=int, default=5)

parser.add_argument('--start-date', required=False, type=date_type, default=datetime.today(), help="Start Date (YYYY-MM-DD)", )

args = parser.parse_args()

Logger.dark_mode = args.dark_mode
Logger.new_file(args.dsl_filename)

run_MCTS(
    maintenances=globals()['maintenances'], 
    installations=globals()['installations'],
    input=args.input, 
    days_per_period=args.days_per_period, 
    number_of_periods=args.number_of_periods, 
    random_state=args.random_state, 
    additional_hours=args.extra_hours,
    prob_before_schedule=args.prob_before_scheduling, 
    m=args.rate_of_change, 
    max_iter=args.max_iters, 
    out_filename=args.output,
    path=args.path,
    start_date=args.start_date
)
