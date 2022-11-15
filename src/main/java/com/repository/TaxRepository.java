package com.repository;

import com.domain.TaxVariable;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaxRepository {

    private static DataSource dataSource;

    private static final String CREATE = "insert into taxcalculator (TaxPayerCategory,Zone,NetTaxAbleIncome,TaxLiabilityAmount,TotalPayAbleTaxAmount,TotalPayAbleTaxAmountM) values (?,?,?,?,?,?)";

    private static final String ALL = "select * from taxcalculator";

    public TaxRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public static List<TaxVariable> list() throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(ALL);
        return mapper(resultSet);
    }

    public static boolean create(TaxVariable taxVariable) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setString(1,taxVariable.getTaxPayerCategory());
        preparedStatement.setString(2, taxVariable.getZone());
        preparedStatement.setInt(3,taxVariable.getNetTaxAbleIncome());
        preparedStatement.setInt(4,taxVariable.getTaxLiabilityAmount());
        preparedStatement.setInt(5,taxVariable.getTotalPayAbleTaxAmount());
        preparedStatement.setInt(6,taxVariable.getTotalPayAbleTaxAmountM());
        return preparedStatement.execute();
    }
    private static List<TaxVariable> mapper(ResultSet resultSet) throws SQLException {
        List<TaxVariable> taxVariables = new ArrayList<>();

        while(resultSet.next()) {
            TaxVariable taxVariable = new TaxVariable();
            taxVariable.setId(resultSet.getInt(1));
            taxVariable.setTaxPayerCategory(resultSet.getString(2));
            taxVariable.setZone(resultSet.getString(3));
            taxVariable.setNetTaxAbleIncome(resultSet.getInt(4));
            taxVariable.setTaxLiabilityAmount(resultSet.getInt(5));
            taxVariable.setTotalPayAbleTaxAmount(resultSet.getInt(6));
            taxVariable.setTotalPayAbleTaxAmountM(resultSet.getInt(7));

            taxVariables.add(taxVariable);
        }
        return taxVariables;
    }

}
