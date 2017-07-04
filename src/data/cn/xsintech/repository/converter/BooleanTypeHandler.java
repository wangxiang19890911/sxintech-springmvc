package cn.xsintech.repository.converter;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.util.StringUtils;

public class BooleanTypeHandler implements TypeHandler<Boolean> {

	@Override
	public Boolean getResult(ResultSet rs, String columnLabel) throws SQLException {
		String p = rs.getString(columnLabel);
		if (StringUtils.isEmpty(p)) {
			return Boolean.FALSE;
		}
		p = p.toUpperCase();
		if ("Y".equals(p)) {
			return Boolean.TRUE;
		}
		if ("N".equals(p)) {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean getResult(ResultSet rs, int columnIndex) throws SQLException {
		String p = rs.getString(columnIndex);
		if (StringUtils.isEmpty(p)) {
			return Boolean.FALSE;
		}
		p = p.toUpperCase();
		if ("Y".equals(p)) {
			return Boolean.TRUE;
		}
		if ("N".equals(p)) {
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

	@Override
	public Boolean getResult(CallableStatement rs, int columnIndex) throws SQLException {
		return null;
	}

	@Override
	public void setParameter(PreparedStatement rs, int parameterIndex, Boolean bool, JdbcType jdbcType) throws SQLException {
		rs.setString(parameterIndex, (bool ? "Y" : "N"));
	}

}
