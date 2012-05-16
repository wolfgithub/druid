package com.alibaba.druid.bvt.sql.cobar;

import org.junit.Assert;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.parser.Token;

import junit.framework.TestCase;

public class DMLDeleteParserTest extends TestCase {

    public void testDelete_0() throws Exception {
        String sql = "deLetE LOW_PRIORITY from id1.id , id using t1 a where col1 =? ";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE LOW_PRIORITY FROM id1.id, id\n" + //
                            "USING t1 a" + //
                            "\nWHERE col1 = ?", output);
    }

    public void testDelete_1() throws Exception {
        String sql = "deLetE from id1.id  using t1";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE FROM id1.id" + //
                            "\nUSING t1", output);
    }

    public void testDelete_2() throws Exception {
        String sql = "delete from offer.*,wp_image.* using offer a,wp_image b where a.member_id=b.member_id and a.member_id='abc' ";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE FROM offer.*, wp_image.*\n" + //
                            "USING offer a, wp_image b\n" + //
                            "WHERE a.member_id = b.member_id\n" + //
                            "AND a.member_id = 'abc'", output);
    }

    public void testDelete_3() throws Exception {
        String sql = "deLetE from id1.id where col1='adf' limit 1,?";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE FROM id1.id\n" + //
                            "WHERE col1 = 'adf'\n" + //
                            "LIMIT 1, ?", output);
    }

    public void testDelete_4() throws Exception {
        String sql = "deLetE from id where col1='adf' ordEr by d liMit ? offset 2";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE FROM id\n" + //
                            "WHERE col1 = 'adf'\n" + //
                            "ORDER BY d\n" + //
                            "LIMIT 2, ?", output);
    }

    public void testDelete_5() throws Exception {
        String sql = "deLetE id.* from t1,t2 where col1='adf'            and col2=1";
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatementList().get(0);
        parser.match(Token.EOF);
        String output = SQLUtils.toMySqlString(stmt);
        Assert.assertEquals("DELETE id.*\n" + //
                            "FROM t1, t2\n" + //
                            "WHERE col1 = 'adf'\n" + //
                            "AND col2 = 1", output);
    }
}
