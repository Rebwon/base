package ko.maeng.oop.ladder.domain;

import ko.maeng.oop.ladder.core.NatualNumber;

public class Ladder {
    private Row[] rows;

    Ladder(NatualNumber height, NatualNumber nthOfPerson) {
        rows = new Row[height.getNumber()];

        for(int i=0 ;i<height.getNumber(); i++) {
            rows[i] = new Row(nthOfPerson);
        }
    }

    void drawLine(NatualNumber height, NatualNumber startPosition) {
        if(isOverHeight(height)) {
            throw new IllegalArgumentException();
        }
        rows[height.toArrayIndex()].drawLine(startPosition);
    }

    private boolean isOverHeight(NatualNumber height) {
        return height.toArrayIndex() > rows.length - 1;
    }

    Marker run(Marker nthOfPerson) {
        for(int i = 0; i< rows.length; i++) {
            Row row = rows[i];
            nthOfPerson = row.move(nthOfPerson);
            String result = generate(rows, Position.createFromArrayIndex(i, nthOfPerson.toArrayIndex()));
            System.out.println(result);
        }
        return nthOfPerson;
    }

    static String generate(Row[] rows, Position position) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            row.generateRow(sb, i, position);
        }
        return sb.toString();
    }
}
