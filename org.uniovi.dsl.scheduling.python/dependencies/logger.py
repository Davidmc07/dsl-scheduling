import re

class Logger:
    section_separator_length = 80

    @staticmethod
    def message(msg):
        print(msg, flush=True)

    @staticmethod
    def step(msg):
        print('  - ' + msg, flush=True)

    @staticmethod
    def open_section(title):
        print(f'[+] \033[33m{title}\033[0m')
        print('=' * Logger.section_separator_length, flush=True)

    def separator():
        print('-' * Logger.section_separator_length, flush=True)

    @staticmethod
    def close_section():
        print('\n', flush=True)

    @staticmethod
    def done():
        print('\n[\033[32mDone\033[0m]', flush=True)

    @staticmethod
    def scores(score_dict, indentation=2):
        max_length = max(score_dict.keys(), key=lambda x: len(x))
        space = " " * indentation
        for name, score in enumerate(score_dict):
            print(f"{space}{name}:  " + " "*(max_length-len(name)), sep='')
            print(f"[ {score} ]")

    @staticmethod
    def new_file(filename):
        Logger.box(f"Executing file: [ \033[35m{filename}\033[0m ]")
        print("\n", flush=True)

    @staticmethod
    def box(text):
        length = 2 + len(re.sub(r"\033\[[0-9]*m", "", text))
        print('\u2554'+ '\u2550'*length +'\u2557')
        print(f"\u2551 {text} \u2551")
        print('\u255A'+ '\u2550'*length +'\u255D', flush=True)
