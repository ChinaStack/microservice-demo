FROM registry.cn-beijing.aliyuncs.com/edas_unified/jct_dw_8_4_4:0.1

WORKDIR /root/
RUN wget "https://dtsre-prod.oss-cn-hangzhou.aliyuncs.com/Agent/ArmsAgent.zip" -O ArmsAgent.zip
RUN unzip ArmsAgent.zip -d /root/
RUN sed -i "s#profiler.collector.ip=127.0.0.1#profiler.collector.ip=47.96.250.95#g" /root/ArmsAgent/arms-agent.config
ENV arms_licenseKey=1@c2e545db8736b0c
ENV arms_appName=productservice
ENV JAVA_TOOL_OPTIONS ${JAVA_TOOL_OPTIONS} '-javaagent:/root/ArmsAgent/arms-bootstrap-1.7.0-SNAPSHOT.jar -Darms.licenseKey='${arms_licenseKey}' -Darms.appName='${arms_appName}
# copy arthas
COPY --from=hengyunabc/arthas:latest /opt/arthas /opt/arthas

WORKDIR /app
COPY /productservice-provider/target/productservice-provider-1.0.0-SNAPSHOT.jar /app
COPY /start.sh /app

EXPOSE 8080
CMD ["/app/start.sh"]
