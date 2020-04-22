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
            String result = generate(rows, new NatualNumber(i+1), nthOfPerson);
            System.out.println(result);
        }
        return nthOfPerson;
    }

    static String generate(Row[] rows, NatualNumber height, NatualNumber nthOfPerson) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            row.generateRow(sb, i, height, nthOfPerson);
        }
        return sb.toString();
    }
}
