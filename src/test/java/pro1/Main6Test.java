package pro1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Main6Test
{
    @Test
    void test01()
    {
        // TODO 6.2: Oprav test
        assertEquals(
            244417,
                Main6.idOfBestTeacher("KIKM",2024)
        );
    }
}