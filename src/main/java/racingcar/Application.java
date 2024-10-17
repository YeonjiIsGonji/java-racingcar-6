package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.*;

public class Application {
    /**
     * 1. 입력 받은 이동 횟수만큼 함수(랜덤 숫자 생성) 반복
     * 2. move 함수 (랜덤 숫자 생성 - 4이상이면 전진 "-" 출력)
     * 3. 우승자 확인
     */
    public static void main(String[] args) {
        List<List<String>> carMovement = new ArrayList<>();
        List<List<Integer>> indexedMovements = new ArrayList<>();

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String str = Console.readLine();
        String[] nameList = str.split(",");

        // 오류 발생
        for (String name : nameList) {
            if (name.length() > 5) {
                throw new IllegalArgumentException();
            }
        }

        System.out.println("시도할 회수는 몇회인가요?");
        int count = Integer.parseInt(Console.readLine());

        for (int i = 0; i < nameList.length; i++) {
            carMovement.add(new ArrayList<>());
            indexedMovements.add(new ArrayList<>());
        }

        System.out.println();
        System.out.println("실행 결과");

        // 랜덤으로 이동 -> 출력
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < nameList.length; j++) {
                System.out.print(nameList[j] + " : ");
                randomMove(carMovement, j);
            }
            System.out.println();
        }

        // 승자 확인하기
        List<Integer> winnerIndex = winnerFind(carMovement, indexedMovements);
        List<String> winnerNameList = new ArrayList<>();

        for (Integer index : winnerIndex) {
            winnerNameList.add(nameList[index]);
        }

        System.out.println("최종 우승자 : " + String.join(", ", winnerNameList));

    }

    public static void randomMove(List<List<String>> carMovement, int j) {
        int randomNum = Randoms.pickNumberInRange(0, 9);
        if (randomNum >= 4) {
            carMovement.get(j).add("-");
        }
        for (String movement : carMovement.get(j)) {
            System.out.print(movement);
        }
        System.out.print("\n");
    }

    public static List<Integer> winnerFind(List<List<String>> carMovement, List<List<Integer>> indexedMovements) {

        for (int i = 0; i < carMovement.size(); i++) {
            int movingCount = carMovement.get(i).size();
            indexedMovements.get(i).add(i);
            indexedMovements.get(i).add(movingCount);
        }

        indexedMovements.sort((list1, list2) -> Integer.compare(list2.get(1), list1.get(1)));

        int maxMoving = indexedMovements.get(0).get(1);

        List<Integer> winnerIndex = new ArrayList<>();

        for (List<Integer> car : indexedMovements) {
            if (car.get(1) == maxMoving) {
                winnerIndex.add(car.get(0));
            }
        }


        return winnerIndex;
    }
}
