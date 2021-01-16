FROM hseeberger/scala-sbt:graalvm-ce-20.3.0-java8_1.4.4_2.12.12

ENV UI_TYPE=tui

ADD . /Silverselbst
WORKDIR /Silverselbst
RUN sbt compile

CMD sbt run