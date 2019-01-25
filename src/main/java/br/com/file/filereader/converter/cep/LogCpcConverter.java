package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogCpc;


/**
 * Classe de conversão de layout do arquivo de LOG_BAIRRO de CEP.
 *
 * @author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:00:00
 */
public class LogCpcConverter implements Converter<LogCpc, Long> {

    private static final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_CPC";
    private static final String columns = "CPC_NU, UFE_SG, LOC_NU, CPC_NO, CPC_ENDERECO, CEP, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogCpc convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogCpc logCpc = new LogCpc();

        logCpc.setCpcNu(getNumber(splited, 0));
        logCpc.setUfeSg(getString(splited, 1));
        logCpc.setLocNu(getNumber(splited, 2));
        logCpc.setCpcNo(getString(splited, 3));
        logCpc.setCpcEndereco(getString(splited, 4));
        logCpc.setCep(getString(splited, 5));

        return logCpc;
    }

    @Override
    public StringBuilder generateSQL(LogCpc logCpc) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logCpc.getCpcNu() != 0) {

            SqlGenerateUtil.insertInit(sql, esquema, table, columns + columnsSys);

            sql.append(logCpc.getCpcNu()).append(", ");
            sql.append("'").append(escapeSpecial(logCpc.getUfeSg())).append("', ");
            sql.append(logCpc.getLocNu()).append(", ");
            sql.append("'").append(escapeSpecial(logCpc.getCpcNo())).append("', ");
            sql.append("'").append(escapeSpecial(logCpc.getCpcEndereco())).append("', ");
            sql.append("'").append(escapeSpecial(logCpc.getCep())).append("', ");
            sql.append("USER, ");
            sql.append("current_timestamp");

            SqlGenerateUtil.insertFinish(sql);
        }

        if ((contador % numCommits) == 0) SqlGenerateUtil.commit(sql);

        return sql;
    }

    @Override
    public void countRegistros(StringBuilder stringBuilder) {
        SqlGenerateUtil.countRegistrosInseridos(stringBuilder, esquema, table, "");
    }

}
