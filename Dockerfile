FROM hseeberger/scala-sbt:11.0.9.1_1.4.4_2.12.12

RUN apt-get update
RUN apt install -y sbt libxrender1 libxtst6 libxi6
ADD . /Silverselbst
WORKDIR /Silverselbst
RUN sbt compile

CMD sbt run

# docker build -t ma161mol/silver .
# docker run -it --rm -e DISPLAY=10.13.0.122:0.0 ma161mol/silver
#
