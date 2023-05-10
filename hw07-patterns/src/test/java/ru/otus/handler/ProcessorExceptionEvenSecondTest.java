package ru.otus.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.processor.DateTimeProvider;
import ru.otus.processor.Processor;
import ru.otus.processor.ProcessorExceptionEvenSecond;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

public class ProcessorExceptionEvenSecondTest {

    @Test
    @DisplayName("Тестируем корректную работу в нечетную секунду")
    void handleProcessorsTest() {
        //given
        var message = new Message.Builder(1L).field7("field7").build();
        var processor = new ProcessorExceptionEvenSecond(() -> LocalDateTime.of(2000, 01, 01, 01, 01, 01));
        var result = processor.process(message);

        //verify
        assertThat(result).isEqualTo(message);
    }

    @Test
    @DisplayName("Тестируем обработку исключения в четную секунду")
    void handleExceptionTest() {
        //given
        var message = new Message.Builder(1L).field7("field7").build();
        var processor = new ProcessorExceptionEvenSecond(() -> LocalDateTime.of(2000, 01, 01, 01, 01, 02));

        //verify
        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> processor.process(message));
    }

}
