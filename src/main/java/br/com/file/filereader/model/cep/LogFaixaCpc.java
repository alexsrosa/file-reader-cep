package br.com.file.filereader.model.cep;

public class LogFaixaCpc {

  private Long cpcNu;
  private String cpcInicial;
  private String cpcFinal;
  private String usuarioInsercao;
  private java.sql.Timestamp dataInsercao;
  private String usuarioAlteracao;
  private java.sql.Timestamp dataAlteracao;
  private String registroExcluido;
  private String usuarioExclusao;
  private java.sql.Timestamp dataExclusao;


  public Long getCpcNu() {
    return cpcNu;
  }

  public void setCpcNu(Long cpcNu) {
    this.cpcNu = cpcNu;
  }


  public String getCpcInicial() {
    return cpcInicial;
  }

  public void setCpcInicial(String cpcInicial) {
    this.cpcInicial = cpcInicial;
  }


  public String getCpcFinal() {
    return cpcFinal;
  }

  public void setCpcFinal(String cpcFinal) {
    this.cpcFinal = cpcFinal;
  }


  public String getUsuarioInsercao() {
    return usuarioInsercao;
  }

  public void setUsuarioInsercao(String usuarioInsercao) {
    this.usuarioInsercao = usuarioInsercao;
  }


  public java.sql.Timestamp getDataInsercao() {
    return dataInsercao;
  }

  public void setDataInsercao(java.sql.Timestamp dataInsercao) {
    this.dataInsercao = dataInsercao;
  }


  public String getUsuarioAlteracao() {
    return usuarioAlteracao;
  }

  public void setUsuarioAlteracao(String usuarioAlteracao) {
    this.usuarioAlteracao = usuarioAlteracao;
  }


  public java.sql.Timestamp getDataAlteracao() {
    return dataAlteracao;
  }

  public void setDataAlteracao(java.sql.Timestamp dataAlteracao) {
    this.dataAlteracao = dataAlteracao;
  }


  public String getRegistroExcluido() {
    return registroExcluido;
  }

  public void setRegistroExcluido(String registroExcluido) {
    this.registroExcluido = registroExcluido;
  }


  public String getUsuarioExclusao() {
    return usuarioExclusao;
  }

  public void setUsuarioExclusao(String usuarioExclusao) {
    this.usuarioExclusao = usuarioExclusao;
  }


  public java.sql.Timestamp getDataExclusao() {
    return dataExclusao;
  }

  public void setDataExclusao(java.sql.Timestamp dataExclusao) {
    this.dataExclusao = dataExclusao;
  }

}
