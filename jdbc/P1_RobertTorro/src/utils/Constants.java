package utils;


public class Constants {
	public static final String SELECT_USUARI_CORRECTA = "SELECT IF((SELECT nombre from jugador where password = ? AND nombre = ?) IS NOT NULL,(select id from jugador where nombre = ?),0);";
	public static final String INSERT_CREAR_USUARI = "insert into jugador values(null,?,?,?,0,0);";
	public static final String SELECT_USUARI_SENSE_CARTES = "SELECT IF((SELECT MAX(id) from carta where id_jugador = ?) IS NOT NULL,1,0);";
	public static final String SELECT_ULTIMA_CARTA = "select * from carta where id = (select id_carta from partida where id = (select max(id) from partida));";
	public static final String INSERT_ROBAR_CARTA = "insert into carta value(null,?,?,?);";
	public static final String SELECT_SHOW_CARTES = "SELECT * FROM carta WHERE id_jugador = ? and id NOT IN (select id_carta from partida where estat = 0 or estat = 1);";
	public static final String SELECT_SHOW_CARTES_NO_JUGADA = "SELECT * FROM carta WHERE id_jugador = ?;";
	public static final String UPDATE_CARTA_SELECIONADA = "update partida set estat = 1 where id = (select max(id)from partida);";
	public static final String INSERT_CARTA_SELECIONADA = "insert into partida value(null,(select id from carta where id = ?),0);";
	public static final String UPDATE_ULTIMA_CARTA_EXCEPCIONS = "update partida set estat = 1 where id = (select max(id)from partida);";
	public static final String SELECT_ULTIMA_JUGADA = "SELECT IF((Select max(id) from partida where estat = 0) IS NOT NULL,1,0);";
	public static final String SELECT_MATEIX_COLOR = "select color from carta where id = ?;";
	public static final String UPDATE_FINALITZA_PARTIDA_PARTIDAS = "update jugador set partidas = partidas + 1 ;";
	public static final String UPDATE_FINALITZA_PARTIDA_GANADAS = "update jugador set ganadas = ganadas + 1 where id = ? ;";
	public static final String DELETE_FINALITZA_PARTIDA_PARTIDA = "delete from partida";
	public static final String DELETE_FINALITZA_PARTIDA_CARTA = "delete from carta";

	
}
