# 基于哪个镜像
FROM centos:7

# 作者
MAINTAINER "3546514206@qq.com"

# 设置环境变量
ENV ROOT_PASSWORD Yhb199605

# yum安装openssh-server、openssh-clients、net-tools
RUN yum install -y openssh-server
RUN yum install -y openssh-clients
RUN yum install -y net-tools

# 设置root账号的密码
RUN echo $ROOT_PASSWORD | passwd --stdin root

# 生成ssh的key
RUN ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key
RUN ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key

# 容器启动时，启动sshd服务
CMD ["/usr/sbin/sshd", "-D"]

# 监听22端口
EXPOSE 22



# docker build -t 'yang/centos-ssh' .
# docker run -itd --name developer -v /Users/setsunayang/Documents/docker/centos/home:/home -p 23:22 yang/centos-ssh