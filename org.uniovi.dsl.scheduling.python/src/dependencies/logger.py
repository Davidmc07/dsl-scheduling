import re

class Logger:
    section_separator_length = 80
    dark_mode = False
    COLORS = {
        "section": {"light": "\033[38;2;0;0;255m", "dark": "\033[38;2;255;255;0m"},
        "done": {"light": "\033[38;2;10;135;0m", "dark": "\033[38;2;0;255;0m"},
        "filename": {"light": "\033[38;2;230;0;115m", "dark": "\033[38;2;255;0;255m"}
    }
    @staticmethod
    def _get_color(element):
        theme = "dark" if Logger.dark_mode else "light"
        return Logger.COLORS[element][theme]

    @staticmethod
    def message(msg):
        print(msg, flush=True)

    @staticmethod
    def step(msg):
        print('  - ' + msg, flush=True)

    @staticmethod
    def open_section(title):
        print(f'[+] {Logger._get_color("section")}{title}\033[0m')
        print('=' * Logger.section_separator_length, flush=True)

    def separator():
        print('-' * Logger.section_separator_length, flush=True)

    @staticmethod
    def close_section():
        print('\n', flush=True)

    @staticmethod
    def done():
        print(f'\n[{Logger._get_color("done")}Done\033[0m]', flush=True)

    @staticmethod
    def scores(score_dict, indentation=2):
        max_length = max(score_dict.keys(), key=lambda x: len(x))
        space = " " * indentation
        for name, score in enumerate(score_dict):
            print(f"{space}{name}:  " + " "*(max_length-len(name)), sep='')
            print(f"[ {score} ]")

    @staticmethod
    def new_file(filename):
        Logger.box(f'Executing file: [ {Logger._get_color("filename")}{filename}\033[0m ]')
        print("\n", flush=True)

    @staticmethod
    def box(text):
        length = 2 + len(re.sub(r"\033\[[0-9;]*m", "", text))
        print('\u2554'+ '\u2550'*length +'\u2557')
        print(f"\u2551 {text} \u2551")
        print('\u255A'+ '\u2550'*length +'\u255D', flush=True)
