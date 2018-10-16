#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Filename : sched_demo
# @Date : 2018-10-14-02-10
# @Project: pydemos
# @AUTHOR : losymear

import sched, time
from queue import Queue

if __name__ == '__main__':
    pass

s = sched.scheduler(time.time, time.sleep)


def print_time(a='default', delay=False):
    if delay:
        time.sleep(15)
    print("From print_time", time.time(), a)


def print_some_times():
    print(time.time())
    s.enter(10, 1, print_time)
    s.enter(5, 2, print_time, argument=('positional', True))
    s.enter(5, 1, print_time, kwargs={'a': 'keyword'})
    s.run()
    print(time.time())


print_some_times()


Queue
