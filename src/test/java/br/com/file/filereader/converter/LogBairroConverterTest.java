package br.com.file.filereader.converter;

import br.com.file.filereader.converter.cep.LogBairroConverter;
import br.com.file.filereader.model.cep.LogBairro;
import org.junit.Assert;
import org.junit.Test;

public class LogBairroConverterTest {

    private LogBairro getLogBairro(String line) {
        return new LogBairroConverter().convertLineToObject(line);
    }

    @Test
    public void convertLineToObjetcTest_1(){

        LogBairro logBairro = getLogBairro("49922@AC@16@Paz@Paz");

        Assert.assertEquals(logBairro.getBaiNu(), 49922, 0.003);
        Assert.assertEquals(logBairro.getUfeSg(), "AC");
        Assert.assertEquals(logBairro.getLocNu(), 16, 0.003);
        Assert.assertEquals(logBairro.getBaiNo(), "Paz");
        Assert.assertEquals(logBairro.getBaiNoAbrev(), "Paz");
    }

    @Test
    public void convertLineToObjetcTest_2(){

        LogBairro logBairro = getLogBairro("33@AC@16@João Eduardo I@J Eduardo I");

        Assert.assertEquals(logBairro.getBaiNu(), 33, 0.003);
        Assert.assertEquals(logBairro.getUfeSg(), "AC");
        Assert.assertEquals(logBairro.getLocNu(), 16, 0.003);
        Assert.assertEquals(logBairro.getBaiNo(), "João Eduardo I");
        Assert.assertEquals(logBairro.getBaiNoAbrev(), "J Eduardo I");
    }

    @Test
    public void convertLineToObjetcTest_3(){

        LogBairro logBairro = getLogBairro("14220@SE@8770@Salgado Filho@S Filho");

        Assert.assertEquals(logBairro.getBaiNu(), 14220, 0.003);
        Assert.assertEquals(logBairro.getUfeSg(), "SE");
        Assert.assertEquals(logBairro.getLocNu(), 8770, 0.003);
        Assert.assertEquals(logBairro.getBaiNo(), "Salgado Filho");
        Assert.assertEquals(logBairro.getBaiNoAbrev(), "S Filho");
    }




}
