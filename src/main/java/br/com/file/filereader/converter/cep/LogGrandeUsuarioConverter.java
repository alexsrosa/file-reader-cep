package br.com.file.filereader.converter.cep;

import br.com.file.filereader.converter.Converter;
import br.com.file.filereader.helper.SqlGenerateUtil;
import br.com.file.filereader.model.cep.LogGrandeUsuario;

/**
 * Classe de conversão de layout do arquivo de LOG_GRANDE_USUARIO de CEP.
 *
 * @@author <a href="mailto:alexrosa@ntconsult.com.br">alexrosa</a>
 * @since 01/02/18 09:03:00
 */
public class LogGrandeUsuarioConverter implements Converter<LogGrandeUsuario, Long> {

    private final String separador = "@";
    private static final String esquema = "SEFAZ_CEP";
    private static final String table = "LOG_GRANDE_USUARIO";
    private static final String columns = "GRU_NU, UFE_SG, LOC_NU, BAI_NU, LOG_NU, GRU_NO, GRU_ENDERECO, CEP, "
            + "GRU_NO_ABREV, ";
    private static final String columnsSys = "USUARIO_INSERCAO, DATA_INSERCAO";
    private int contador = 0;
    private int numCommits = 1000;

    @Override
    public LogGrandeUsuario convertLineToObject(String line) {

        String[] splited = line.split(separador);

        LogGrandeUsuario logGrandeUsuario = new LogGrandeUsuario();

        logGrandeUsuario.setGruNu(getNumber(splited, 0));
        logGrandeUsuario.setUfeSg(getString(splited, 1));
        logGrandeUsuario.setLocNu(getNumber(splited, 2));
        logGrandeUsuario.setBaiNu(getNumber(splited, 3));
        logGrandeUsuario.setLogNu(
                getNumber(splited, 4) != 0 ? getNumber(splited, 4) : null);
        logGrandeUsuario.setGruNo(getString(splited, 5));
        logGrandeUsuario.setGruEndereco(getString(splited, 6));
        logGrandeUsuario.setCep(getString(splited, 7));
        logGrandeUsuario.setGruNoAbrev(getString(splited, 8));

        return logGrandeUsuario;
    }

    @Override
    public StringBuilder generateSQL(LogGrandeUsuario logGrandeUsuario) {

        StringBuilder sql = new StringBuilder();

        contador++;

        if (logGrandeUsuario.getGruNu() != 0) {
            SqlGenerateUtil.insertInit(sql, esquema, table, columns+columnsSys);

            sql.append(logGrandeUsuario.getGruNu()).append(", ");
            sql.append("'").append(escapeSpecial(logGrandeUsuario.getUfeSg())).append("', ");
            sql.append(logGrandeUsuario.getLocNu()).append(", ");
            sql.append(logGrandeUsuario.getBaiNu()).append(", ");
            sql.append(logGrandeUsuario.getLogNu()).append(", ");
            sql.append("SUBSTR('").append(escapeSpecial(logGrandeUsuario.getGruNo())).append("', 0,72), ");
            sql.append("'").append(escapeSpecial(logGrandeUsuario.getGruEndereco())).append("', ");
            sql.append("'").append(escapeSpecial(logGrandeUsuario.getCep())).append("', ");
            sql.append("SUBSTR('").append(escapeSpecial(logGrandeUsuario.getGruNoAbrev())).append("', 0,36), ");
            sql.append("USER, ");
            sql.append("current_timestamp");

            SqlGenerateUtil.insertFinish(sql);
        }

        if ((contador % numCommits) == 0) SqlGenerateUtil.commit(sql);

        return sql;
    }

    @Override
    public void countRegistros(StringBuilder stringBuilder) {
        SqlGenerateUtil.countRegistrosInseridos(stringBuilder, esquema, table,"");
    }
}
