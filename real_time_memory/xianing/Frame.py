import time
import tkinter as tk
from tkinter import ttk
import psutil


class Frame(object):
    def init(self):
        self.window = tk.Tk()
        self.window.title('XIANING')
        # 获取屏幕宽高
        x_with = self.window.winfo_screenwidth()
        y_height = self.window.winfo_screenheight()
        # 设置窗体宽高
        ww = 110
        wh = 110
        self.window.geometry("%dx%d+%d+%d" % (ww, wh, x_with - ww - 10, y_height - wh - 70))
        # 窗体背景色
        themecolcor = "white"
        # 设置窗体背景色白色
        self.window["background"] = themecolcor
        # 设置窗口只有推出按钮
        self.window.attributes("-toolwindow", True)
        # 窗体置顶
        self.window.attributes("-topmost", True)
        # 窗体大小不可变
        self.window.resizable(width=False, height=False)
        self.label = tk.Label(self.window, text="253", bg=themecolcor, font=('Arial', 10))
        self.label.pack()
        self.progressbar = ttk.Progressbar(self.window, length=100, cursor='spider', mode="determinate", orient=tk.HORIZONTAL)
        self.progressbar.pack()
        self.labelcpu = tk.Label(self.window, text="", bg=themecolcor, font=('Arial', 10))
        self.labelcpu.pack()
        self.progressbarcpu = ttk.Progressbar(self.window, length=100, cursor='spider', mode="determinate", orient=tk.HORIZONTAL)
        self.progressbarcpu.pack()
        # 网速检测
        self.oldrev=psutil.net_io_counters().packets_recv
        self.oldsent=psutil.net_io_counters().packets_sent
        self.labelnet = tk.Label(self.window, text="", bg=themecolcor, font=('Arial', 10))
        self.labelnet.pack()
        self.update_data()
        self.window.mainloop()

    def update_data(self):
        # 网速
        newnetrev=psutil.net_io_counters().packets_recv
        newnetsent=psutil.net_io_counters().packets_sent
        revspeed=newnetrev-self.oldrev
        sendspeed=newnetsent-self.oldsent
        strrev=str(revspeed)+'k/s'
        strsend=str(sendspeed)+'k/s'
        if revspeed>=1024:
            revspeed=revspeed/1024
            strrev=str(format(revspeed,'.1f'))+'M/s'
        if sendspeed>=1024:
            sendspeed=sendspeed/1024
            strsend=str(format(sendspeed,'.1f'))+'M/s'
        # 换算成G
        if revspeed >= 1048576:
            revspeed = revspeed / 1048576
            strrev = str(format(revspeed, '.2f')) + 'G/s'
        if sendspeed >= 1048576:
            sendspeed = sendspeed / 1048576
            strsend = str(format(sendspeed, '.2f')) + 'G/s'
        self.labelnet.configure(text='↑'+strsend+' ↓'+strrev)
        self.oldrev=newnetrev
        self.oldsent=newnetsent
        # 内存
        info = psutil.virtual_memory().percent
        tex = '内存: ' + str(info) + '%'
        self.label.configure(text=tex)
        self.progressbar["value"] = 100 * info * 0.01
        # cpu
        cpu_percent=psutil.cpu_percent(interval=1, percpu=False)
        texcpu = 'cpu : ' + str(cpu_percent) + '%'
        self.labelcpu.configure(text=texcpu)
        self.progressbarcpu["value"] = 100 * cpu_percent * 0.01

        self.window.after(1000, self.update_data)


frame = Frame()
frame.init()




