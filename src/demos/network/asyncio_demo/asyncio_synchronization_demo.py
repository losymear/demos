#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Filename : asyncio_synchronization_demo
# @Date : 2018-10-16-18-13
# @Project: pydemos
# @AUTHOR : losymear


import asyncio
import time


class LockTest:
    lock: asyncio.Lock

    async def waiter(self):
        print('{message:30} {time:s}'.format(message="waiting for event...", time=time.strftime("%X")))
        async with self.lock:
            print('{message:30} {time:s}'.format(message="... got lock after 2s! ...", time=time.strftime("%X")))

    async def main(self):
        self.lock = asyncio.Lock()
        waiter_task = asyncio.create_task(self.waiter())
        await self.lock.acquire()
        await asyncio.sleep(2)
        self.lock.release()
        await waiter_task


class EventTest:
    # cannot set event in__init__, or will raise an error:
    # got Future <Future pending> attached to a different loop
    event: asyncio.Event

    # Create an Event object.

    async def waiter(self):
        self.event = asyncio.Event()
        print('{message:30} {time:s}'.format(message="waiting for event...", time=time.strftime("%X")))
        await self.event.wait()
        print('{message:30} {time:s}'.format(message="... event set after 3s!...", time=time.strftime("%X")))

    async def main(self):
        # Spawn a Task to wait until 'event' is set.
        waiter_task = asyncio.create_task(self.waiter())

        # Sleep for 1 second and set the event.
        await asyncio.sleep(3)
        self.event.set()

        # Wait until the waiter task is finished.
        await waiter_task


class ConditionTest:
    condition: asyncio.Condition

    async def girl(self):
        await self.condition.acquire()
        print("Hey Nhor, I'm Amy")
        self.condition.notify()
        await self.condition.wait()
        print("Nice to meet you too, Nhor")
        self.condition.notify()
        await self.condition.wait()
        print("Well, you're really like a dirty pig I've seen. So amazing!")
        self.condition.release()

    async def boy(self):
        await self.condition.acquire()
        print("Hay girl, I'm Nhor")
        self.condition.notify()
        await self.condition.wait()
        print("Nice to meet you, Amy")
        self.condition.notify()
        await self.condition.wait()
        print("Amy, you look so fat!")
        self.condition.notify()
        self.condition.release()

    async def main(self):
        self.condition = asyncio.Condition()
        boy_task = asyncio.create_task(self.boy())
        girl_task = asyncio.create_task(self.girl())
        await girl_task
        await boy_task


class SemaphoreTest:
    semaphore: asyncio.Semaphore

    async def worker(self, sleep_time: int, i: int) -> None:
        await asyncio.sleep(sleep_time)
        await self.semaphore.acquire()
        print("worker {} sleep {}s {}".format(sleep_time, i, time.strftime("%X")))

    async def main(self):
        self.semaphore = asyncio.Semaphore(3)
        print('{message:30} {time:s}'.format(message="starting workers...", time=time.strftime("%X")))
        worker0 = asyncio.create_task(self.worker(0, 0))
        worker1 = asyncio.create_task(self.worker(1, 1))
        worker2 = asyncio.create_task(self.worker(2, 2))
        # run 3 tasks concurrently
        await asyncio.gather(worker0, worker1, worker2)
        # semaphore now is locked
        print("semaphore is locked:", self.semaphore.locked())


print("start testing lock")
asyncio.run(LockTest().main())
# waiting for event...           02:33:03
# ... got lock after 2s! ...     02:33:05

print("\n\nstart testing event")
asyncio.run(EventTest().main())
# waiting for event...           02:33:05
# ... event set after 3s!...     02:33:08

print("\n\nUse Condition to make a dialog, in turn to notify() and wait")
asyncio.run(ConditionTest().main())
# Hay girl, I'm Nhor
# Hey Nhor, I'm Amy
# Nice to meet you, Amy
# Nice to meet you too, Nhor
# Amy, you look so fat!
# Well, you're really like a dirty pig I've seen. So amazing!

print("\n\nstart testing semaphore")
asyncio.run(SemaphoreTest().main())
# starting workers...            02:33:08
# worker 0 sleep 0s 02:33:08
# worker 1 sleep 1s 02:33:09
# worker 2 sleep 2s 02:33:10
# semaphore is locked: True
