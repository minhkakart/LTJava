package querry_builder;

import java.util.Map;
import java.util.Vector;

public class QuerryBuilder {

	private String querry;

	public QuerryBuilder() {
		this.querry = "";
	}

	public QuerryBuilder select(String... fields) {
		this.querry += "SELECT ";
		for (int i = 0; i < fields.length; i++) {
			this.querry += fields[i];
			if (i < fields.length - 1) {
				this.querry += ", ";
			}
		}
		return this;
	}

	public QuerryBuilder selectAll() {
		this.querry += "SELECT *";
		return this;
	}

	public QuerryBuilder update(String table) {
		this.querry += "UPDATE " + table;
		return this;
	}

	public QuerryBuilder deleteFrom(String table) {
		this.querry += "DELETE FROM " + table;
		return this;
	}

	public QuerryBuilder insertInto(String table) {
		this.querry += "INSERT INTO " + table;
		return this;
	}

	public QuerryBuilder insertInto(String table, String... fields) {
		this.querry += "INSERT INTO " + table + " (";
		for (int i = 0; i < fields.length; i++) {
			this.querry += fields[i];
			if (i < fields.length - 1) {
				this.querry += ", ";
			}
		}
		this.querry += ")";
		return this;
	}

	public QuerryBuilder insertInto(String table, Vector<String> fields) {
		this.querry += "INSERT INTO " + table + " (";
		for (int i = 0; i < fields.size(); i++) {
			this.querry += fields.get(i);
			if (i < fields.size() - 1) {
				this.querry += ", ";
			}
		}
		this.querry += ")";
		return this;
	}
	

	public QuerryBuilder buildInsert(String table, Vector<String> fields) {
		this.querry += "INSERT INTO " + table + " (";
		for (int i = 0; i < fields.size(); i++) {
			this.querry += fields.get(i);
			if (i < fields.size() - 1) {
				this.querry += ", ";
			}
		}
		this.querry += ")";
		return this;
	}

	public QuerryBuilder buildInsert(String table, Map<String, String> data) {
		this.querry += "INSERT INTO " + table + " (";
		int i = 0;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			this.querry += entry.getKey();
			if (i < data.size() - 1) {
				this.querry += ", ";
			}
			i++;
		}
		this.querry += ")";

		this.querry += " VALUES (";
		i = 0;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			this.querry += "'" + entry.getValue() + "'";
			if (i < data.size() - 1) {
				this.querry += ", ";
			}
			i++;
		}
		this.querry += ")";

		return this;

	}

	public QuerryBuilder set(Map<String, String> data) {
		this.querry += " SET ";
		int i = 0;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			this.querry += entry.getKey() + " = '" + entry.getValue() + "'";
			if (i < data.size() - 1) {
				this.querry += ", ";
			}
			i++;
		}
		return this;
	}

	public QuerryBuilder setPrepared(String... fields) {
		this.querry += " SET ";
		for (int i = 0; i < fields.length; i++) {
			this.querry += fields[i] + " = ?";
			if (i < fields.length - 1) {
				this.querry += ", ";
			}
		}
		return this;
	}

	public QuerryBuilder setPrepared(Vector<String> fields) {
		this.querry += " SET ";
		for (int i = 0; i < fields.size(); i++) {
			this.querry += fields.get(i) + " = ?";
			if (i < fields.size() - 1) {
				this.querry += ", ";
			}
		}
		return this;
	}

	public QuerryBuilder values(String... values) {
		this.querry += " VALUES (";
		for (int i = 0; i < values.length; i++) {
			this.querry += values[i];
			if (i < values.length - 1) {
				this.querry += ", ";
			}
		}
		this.querry += ")";
		return this;
	}

	public QuerryBuilder prepareValues(int count) {
		this.querry += " VALUES (";
		for (int i = 0; i < count; i++) {
			this.querry += "?";
			if (i < count - 1) {
				this.querry += ", ";
			}
		}
		this.querry += ")";
		return this;
	}

	public QuerryBuilder from(String table) {
		this.querry += " FROM " + table;
		return this;
	}

	public QuerryBuilder where(String condition) {
		this.querry += " WHERE " + condition;
		return this;
	}

	public QuerryBuilder where(String field, String operator, String value) {
		this.querry += " WHERE " + field + " " + operator + " '%" + value + "%'";
		return this;
	}

	public QuerryBuilder orderBy(String field) {
		this.querry += " ORDER BY " + field;
		return this;
	}
	
	public QuerryBuilder orderBy(String field, String order) {
		this.querry += " ORDER BY " + field + " " + order;
		return this;
	}
	
	public QuerryBuilder groupBy(String field) {
		this.querry += " GROUP BY " + field;
		return this;
	}
	
	public QuerryBuilder having(String condition) {
		this.querry += " HAVING " + condition;
		return this;
	}
	
	public QuerryBuilder join(String table, String condition) {
		this.querry += " JOIN " + table + " ON " + condition;
		return this;
	}
	
	public QuerryBuilder innerJoin(String table, String condition) {
		this.querry += " INNER JOIN " + table + " ON " + condition;
		return this;
	}
	
	public QuerryBuilder leftJoin(String table, String condition) {
		this.querry += " LEFT JOIN " + table + " ON " + condition;
		return this;
	}
	
	public QuerryBuilder rightJoin(String table, String condition) {
		this.querry += " RIGHT JOIN " + table + " ON " + condition;
		return this;
	}

	public QuerryBuilder limit(int limit) {
		this.querry += " LIMIT " + limit;
		return this;
	}

	public String getQuerry() {
		return this.querry;
	}


}
