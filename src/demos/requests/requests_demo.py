#!/usr/bin/env python
# -*- coding: utf-8 -*-
# @Filename : methods
# @Date : 2018-10-15-11-30
# @Project: pydemos
# @AUTHOR : losymear

"""http方法测试"""

import requests

if __name__ == '__main__':
    pass


def request_get():
    """测试get请求
    使用params参数而不需要手动拼接query string
    """
    r_get = requests.get("https://httpbin.org/get", params={"test": "get"})
    print(r_get.url)
    print(r_get.json().get("args"))
    print(r_get.raw)


def request_post():
    """测试post请求"""
    r_post = requests.post("https://httpbin.org/post", json={"test": "post"})
    print(r_post.json().get("json"))


def request_othermethods():
    """测试其它方法
    head和options相同，不过没有对应的httpbin接口，跳过"""
    r_put = requests.put("https://httpbin.org/put")
    r_delete = requests.delete("https://httpbin.org/delete")
    print(r_put, r_delete, )


request_get()
# request_post()
# request_othermethods()
