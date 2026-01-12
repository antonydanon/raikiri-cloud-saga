# Raikiri Cloud Saga

Это система реализующая распределенную(долгую) транзакцию,
на нескольких микросервисах с имитацией сбоя на одном из этапов,
с использованием conductor:
[https://github.com/conductor-oss/conductor](https://github.com/conductor-oss/conductor) 
[https://github.com/conductor-oss/java-sdk](https://github.com/conductor-oss/java-sdk)

## Как запустить

git clone https://github.com/antonydanon/raikiri-cloud-saga.git
docker compose build
docker compose up