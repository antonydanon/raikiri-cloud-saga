# Raikiri Cloud Saga

Это система реализующая распределенную(долгую) транзакцию,
на нескольких микросервисах с имитацией сбоя на одном из этапов,
с использованием conductor:
[https://github.com/conductor-oss/conductor](https://github.com/conductor-oss/conductor) 
[https://github.com/conductor-oss/java-sdk](https://github.com/conductor-oss/java-sdk)

## Как работает
Распределенная транзакция:
restaurant-api -> courier-api -> payment-api -> notification-api.
Отказ происходит в сервисе payment-api с вероятностью 0,4.
Сервис saga-manager-api инициирует распределенные транзакции каждые 15 секунд и
сохраняет информацию транзакции по завершению, фиксируя время и статус.

Пример запроса, чтобы получить информацию по транзакциям:

`curl 'http://localhost:8081/saga-manager-api/transactions?statuses=COMPLETED,CANCELED&startDate=2026-01-12 00:00:00&endDate=2026-01-19 00:00:00&countPerPage=10&pageNumber=0'`

Eureka server: http://localhost:8761/
API Gateway: http://localhost:8081/
Conductor UI: http://localhost:5000/

## Как запустить
```bash
git clone https://github.com/antonydanon/raikiri-cloud-saga.git
cd raikiri-cloud-saga
docker compose build
docker compose up