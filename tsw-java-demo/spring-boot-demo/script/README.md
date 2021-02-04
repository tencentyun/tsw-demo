# 自动运行脚本设置

### 1. 上传```curl_cron.sh```文件到需要自动发送请求的机器

建议放在和应用jar包同一目录下

### 2. 修改```curl_cron.sh```文件

主要关注```step```（几秒一次）和```curl```后的参数设置

### 3. 设置cron定时任务，注意定时执行的脚本的路径设置

```shell
# 设置cron定时任务
shell > crontab -e

# 填写如下定时任务配置
* * * * * /bin/sh /root/tsw-demo/order/curl_cron.sh > /root/tsw-demo/order/cron.log
```
