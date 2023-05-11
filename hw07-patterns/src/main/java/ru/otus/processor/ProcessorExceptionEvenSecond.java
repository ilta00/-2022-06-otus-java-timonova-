package ru.otus.processor;

import ru.otus.model.Message;

public class ProcessorExceptionEvenSecond implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ProcessorExceptionEvenSecond(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider;
    }

    @Override
    public Message process(Message message) {
        if (dateTimeProvider.getDate().getSecond() % 2 == 0) {
            throw new RuntimeException("Even second!");
        } else {
            var logString = String.format("processMsg:%s", message);
            System.out.println(logString);
            return message;
        }
    }
}
