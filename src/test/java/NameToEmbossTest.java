import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class NameToEmbossTest {
    private String nameToEmboss;
    private boolean expectedResult;

    public NameToEmbossTest(String nameToEmboss, boolean expectedResult) {
        this.nameToEmboss = nameToEmboss;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Object[][] getNameToEmboss() {
        return new Object[][]{
                {"АннаПетрова", false}, // отсутствие пробела
                {"Анна Петрова", true}, // есть пробел + количество символов входящее в диапазон
                {"Анна ПетровнаПетров", true}, // есть пробел + верхняя граница 19 символов
                {"Анна ПетровнаПетрова", false}, // есть пробел + выше верхней границы - 20 символов
                {" Анна ПетровнаПетров", true}, // пробел вначале + количество символов выходящих за диапазон, с пробелом - 20
                {"Анна ПетровнаПетров ", true}, // пробел вконце + количество символов выходящих за диапазон, с пробелом - 20
                {" Анна ПетровнаПетров ", true}, // пробел вначале и вконце + количество символов выходящих за диапазон, с пробелом - 21
                {"Анна Петровна Петр", false}, // 2 пробела + количество символов входящее в диапазон - 18 символов
                {"А а", true}, // есть пробел + нижняя граница 3 символа
                {" А а", true}, // пробел вначале + количество символов входящих в диапазон, с пробелом - 4
                {"А а ", true}, // пробел вконце + количество символов входящих в диапазон, с пробелом - 4
                {" А а ", true}, // пробел вначале и вконце + количество символов входящих в диапазон, с пробелами - 5
                {"       ", false}, // строка из пробелов, входящая в нужный диапазон - 8 символов
                {" Ан", false}, // строка с пробелом вначале, 3 символа с пробелом
                {"Ан ", false}, // строка с пробелом вконце, 3 символа с пробелом
                {" Ан ", false}, // строка с пробелом вначале и вконце, 4 символа с пробелами
                {" Анн", false}, // строка с пробелом вначале, 4 символа с пробелом
                {"Анн ", false}, // строка с пробелом вконце, 4 символа с пробелом
                {" Анн ", false}, // строка с пробелом вначале и вконце, 5 символо с пробелами
                {"1. 2!", true} // цифры и символы, входят в диапазон
        };
    }

    @Test
    public void nameToEmboss() {
        Account account = new Account(nameToEmboss);
        boolean actualResult = account.checkNameToEmboss();
        Assert.assertEquals("Результаты не совпадают",expectedResult, actualResult);
    }
}
