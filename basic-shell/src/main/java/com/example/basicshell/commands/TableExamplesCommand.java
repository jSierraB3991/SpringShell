package com.example.basicshell.commands;

import java.time.LocalDate;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.CellMatchers;
import org.springframework.shell.table.TableBuilder;

import com.example.basicshell.utils.ShellHelper;

import lombok.RequiredArgsConstructor;

@ShellComponent
@RequiredArgsConstructor
public class TableExamplesCommand {

    private final ShellHelper shellHelper;

    @ShellMethod("Display sample tables")
    public void sampleTables() {
        String[] CONTINENTS = {"Europe", "North America", "South America", "Africa", "Asia", "Austraila and Oceania"};
        String[] COUNTRIES1 = {"Germany", "USA", "Brasil", "Nigeria", "China", "Australia"};
        String[] COUNTRIES2 = {"France", "Canada", "Argentina", "Egypt", "India", "New Zeeland"};
         
        Object[][] sampleData = new String[][] {
                CONTINENTS,
                COUNTRIES1,
                COUNTRIES2
        };
        
        var model = new ArrayTableModel(sampleData);
        var tableBuilder = new TableBuilder(model);

        shellHelper.printInfo("air border style");
        tableBuilder.addFullBorder(BorderStyle.air);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("oldschool border style");
        tableBuilder.addFullBorder(BorderStyle.oldschool);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("fancy_light border style");
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("fancy_double border style");
        tableBuilder.addFullBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));

        shellHelper.printInfo("mixed border style");
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double);
        shellHelper.print(tableBuilder.build().render(80));
    }


    @ShellMethod("Table formatter demo")
    public void tableFormatter() {
        var now = LocalDate.now();
        var sampleData = new Object[][] {
            {"Date", "Value"},
            {"Today", now},
            {"Today minus 1", now.minusDays(1)},
            {"Today minus 2", now.minusDays(2)},
            {"Today minus 3", now.minusDays(3)}
        };

        var model = new ArrayTableModel(sampleData);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.on(CellMatchers.ofType(LocalDate.class));
        tableBuilder.addFullBorder(BorderStyle.fancy_light);
        tableBuilder.addInnerBorder(BorderStyle.fancy_light);
        shellHelper.print(tableBuilder.build().render(30));
    }
}
