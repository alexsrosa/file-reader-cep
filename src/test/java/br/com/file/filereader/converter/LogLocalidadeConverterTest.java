package br.com.file.filereader.converter;

import br.com.file.filereader.converter.cep.LogLocalidadeConverter;
import br.com.file.filereader.model.cep.LogLocalidade;
import org.junit.Assert;
import org.junit.Test;

public class LogLocalidadeConverterTest {

    private LogLocalidade getLogLocalidade(String line) {
        return new LogLocalidadeConverter().convertLineToObject(line);
    }

    @Test
    public void convertLineToObjetcTest(){

        LogLocalidade logLocalidade =
                getLogLocalidade("12@AC@Marechal Thaumaturgo@69983000@0@M@@Mal Thaumaturgo@1200351");

        Assert.assertEquals(logLocalidade.getLocNu(), 12, 0.003);
        Assert.assertEquals(logLocalidade.getUfeSg(), "AC");
        Assert.assertEquals(logLocalidade.getLocNo(), "Marechal Thaumaturgo");
        Assert.assertEquals(logLocalidade.getCep(), "69983000");
        Assert.assertEquals(logLocalidade.getLocInSit(), "0");
        Assert.assertEquals(logLocalidade.getLocInTipoLoc(), "M");
        Assert.assertEquals(logLocalidade.getLocNuSub(), 0, 0.003);
        Assert.assertEquals(logLocalidade.getLocNoAbrev(), "Mal Thaumaturgo");
        Assert.assertEquals(logLocalidade.getMunNu(), 1200351, 0.003);
    }



    @Test
    public void convertLineToObjetcTest2(){

        LogLocalidade logLocalidade =
                getLogLocalidade("9805@TO@Araguaína@@1@M@@Araguaína@1702109");

        Assert.assertEquals(logLocalidade.getLocNu(), 9805, 0.003);
        Assert.assertEquals(logLocalidade.getUfeSg(), "TO");
        Assert.assertEquals(logLocalidade.getLocNo(), "Araguaína");
        Assert.assertEquals(logLocalidade.getCep(), "");
        Assert.assertEquals(logLocalidade.getLocInSit(), "1");
        Assert.assertEquals(logLocalidade.getLocInTipoLoc(), "M");
        Assert.assertEquals(logLocalidade.getLocNuSub(), 0, 0.003);
        Assert.assertEquals(logLocalidade.getLocNoAbrev(), "Araguaína");
        Assert.assertEquals(logLocalidade.getMunNu(), 1702109, 0.003);
    }

    @Test
    public void convertLineToObjetcTest3(){

        LogLocalidade logLocalidade =
                getLogLocalidade("9953@TO@Taquarussu do Tocantins@77260000@0@D@9899@Taquarussu Tocantins@");

        Assert.assertEquals(logLocalidade.getLocNu(), 9953, 0.003);
        Assert.assertEquals(logLocalidade.getUfeSg(), "TO");
        Assert.assertEquals(logLocalidade.getLocNo(), "Taquarussu do Tocantins");
        Assert.assertEquals(logLocalidade.getCep(), "77260000");
        Assert.assertEquals(logLocalidade.getLocInSit(), "0");
        Assert.assertEquals(logLocalidade.getLocInTipoLoc(), "D");
        Assert.assertEquals(logLocalidade.getLocNuSub(), 9899, 0.003);
        Assert.assertEquals(logLocalidade.getLocNoAbrev(), "Taquarussu Tocantins");
        Assert.assertEquals(logLocalidade.getMunNu(), 0, 0.003);
    }

    @Test
    public void convertLineToObjetcTest4(){

        LogLocalidade logLocalidade =
                getLogLocalidade("9774@SP@Varpa@@2@D@9748@Varpa@");

        Assert.assertEquals(logLocalidade.getLocNu(), 9774, 0.003);
        Assert.assertEquals(logLocalidade.getUfeSg(), "SP");
        Assert.assertEquals(logLocalidade.getLocNo(), "Varpa");
        Assert.assertEquals(logLocalidade.getCep(), "");
        Assert.assertEquals(logLocalidade.getLocInSit(), "2");
        Assert.assertEquals(logLocalidade.getLocInTipoLoc(), "D");
        Assert.assertEquals(logLocalidade.getLocNuSub(), 9748, 0.003);
        Assert.assertEquals(logLocalidade.getLocNoAbrev(), "Varpa");
        Assert.assertEquals(logLocalidade.getMunNu(), 0, 0.003);
    }
}
