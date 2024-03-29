package org.example.tableBuilder;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.example.exception.InvalidArgumentException;
import org.example.exception.InvalidOperationClass;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class TableBuilder {
    private StringBuilder builder = new StringBuilder();
    private List<Pair<String, Integer>> columns = new ArrayList<>();
    private int rows = 0;
    private int sumLength = 0;

    public class TableBuilderRowClass {
        private TableBuilderRowClass() {}
        public TableBuilderRowClass addRow(List<String> values) {
            if (values.size() != columns.size())
                throw new InvalidArgumentException("Количество значений должно быть равно значению колонок");

            rows++;
            builder.append("|");
            for (int i = 0; i < columns.size(); i++) {
                builder
                        .append(StringUtils.repeat(' ', columns.get(i).getRight() - values.get(i).length() - 1))
                        .append(values.get(i))
                        .append(" |");
            }
            addHorizontalDelimiter();
            return this;
        }

        public String getTable() {
            String tmp = builder.toString();
            clean();
            return tmp;
        }
    }

    public TableBuilder addColumn(String name, int size) {
        if (rows != 0)
            throw new InvalidOperationClass("Нельзя добавить ряд после добавления колонки");

        if (size < name.length())
            size = name.length() + 2;
        columns.add(Pair.of(name, size));

        sumLength = sumRowLength();
        return this;
    }
    public TableBuilderRowClass data() {
        builder.append("| ");
        columns.stream().forEach( (it) ->
            builder.append(it.getLeft())
                    .append(StringUtils.repeat(' ', it.getRight() - it.getLeft().length() - 2))
                    .append(" | ")
        );
        addHorizontalDelimiter();
        return new TableBuilderRowClass();
    }
    private void addHorizontalDelimiter() {
        builder.append('\n')
                .append(StringUtils.repeat('-', sumLength))
                .append('\n');
    }
    private void clean() {
        rows = 0;
        columns = new ArrayList<>();
        builder = new StringBuilder();
    }

    private int sumRowLength() {
        int sum = 0;
        for (int i = 0; i < columns.size(); i++) {
            sum += columns.get(i).getRight();
        }
        return sum + columns.size() + 1;
    }
}
