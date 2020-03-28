import time
import tkinter as tk
import psutil

# # 获取虚拟内存
# info = psutil.virtual_memory()
# print(u'内存使用：', psutil.Process(os.getpid()).memory_info().rss)
# print(u'总内存：', info.total)
# print(info.percent)
# for i in range(0,10):
#     print(u'cpu占比：', psutil.cpu_percent(interval=1, percpu=False))
#
# print(psutil.cpu_count())
#
# for i in range(0,10):
#     print(psutil.net_io_counters())
old = psutil.net_io_counters().packets_recv
time.sleep(1)
for i in range(0, 50):
    new = psutil.net_io_counters().packets_recv
    print(new - old)
    old = new
    time.sleep(1)
