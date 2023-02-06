FROM        centos:centos7.9.2009
MAINTAINER  dys "3546514206@qq.com"
RUN         yum install -y openssh openssh-server openssh-clients
RUN         mkdir -p /var/run/sshd
RUN         ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
RUN         ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key
RUN         /bin/echo 'root:Yhb199605' |chpasswd
RUN         useradd dys
RUN         /bin/echo 'setsunayang:Yhb199605' |chpasswd
RUN         /bin/sed -i 's/.*session.*required.*pam_loginuid.so.*/session optional pam_loginuid.so/g' /etc/pam.d/sshd
RUN         /bin/echo -e "LANG=\"en_US.UTF-8\"" > /etc/default/local
EXPOSE      22
CMD         /usr/sbin/sshd -D

# docker build -rm -t setsunayang/centos:ssh .

# docker run -d -name setsunayang -v /Users/setsunayang/Documents/docker/centos/home:/home -v /Users/setsunayang/Documents/docker/centos/usr:/usr -v /Users/setsunayang/Documents/docker/centos/opt:/opt -v /Users/setsunayang/Documents/docker/centos/bin:/bin -p 23:22 setsunayang/centos:ssh