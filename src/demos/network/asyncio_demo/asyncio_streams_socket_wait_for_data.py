#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Filename : async_streams_socket_wait_for_data
# @Date : 2018-10-16-17-03
# @Project: pydemos
# @AUTHOR : losymear

"""
official example https://docs.python.org/3/library/asyncio-stream.html#examples
Register an open socket to wait for data using streams

"""

import asyncio
import socket


async def wait_for_data():
    # Get a reference to the current event loop because
    # we want to access low-level APIs.
    loop = asyncio.get_running_loop()

    # Create a pair of connected sockets.
    rsock, wsock = socket.socketpair()

    # Register the open socket to wait for data.
    reader, writer = await asyncio.open_connection(sock=rsock)

    # Simulate the reception of data from the network
    loop.call_soon(wsock.send, 'abc'.encode())

    # Wait for data
    data = await reader.read(100)

    # Got data, we are done: close the socket
    print("Received:", data.decode())
    writer.close()

    # Close the second socket
    wsock.close()


asyncio.run(wait_for_data())
