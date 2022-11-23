package ReversiGame;

import java.util.Objects;
import java.util.Scanner;

import static ReversiGame.Constants.*;

/**
 * Управляет ходом игры, взаимодействуя с пользователем, взаимодействует с классами Player и ReversiBoard
 */
public class ReversiGame {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Отслеживает начало новых игр и выход из игры. При первом запуске печатает правила.
     */
    public void startGame() {
        System.out.println(GAME_RULES);
        PlayingField playingField = new PlayingField();
        playingField.createNewGame();

        String userCommand;
        while (true) {
            userCommand = scanner.nextLine();
            switch (userCommand) {
                case "start":
                    communicateWithUser(playingField);
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Неизвестная команда, Чтобы начать новую игру, напишите start. Чтобы выйти из игры, напишите quit");
                    break;
            }
        }
    }

    private static void communicateWithUser(PlayingField playingField) {
        int xCoordinate;
        int yCoordinate;
        // массив из двух координат
        String[] userCoordinatesInput;

        while (true) {
            // Информация о партии
            System.out.printf("Счет:\nЧерные: %d\nБелые: %d\n\nХодят %s",
                    playingField.getGameScore()[0],
                    playingField.getGameScore()[1],
                    playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "черные\n" : "белые\n"
            );
            System.out.println(playingField.renderPlayingFieldIntoString());

            // Валидация координат хода, которые ввел пользователь
            userCoordinatesInput = scanner.nextLine().split(" ");

            // Проверка на два целых числа
            if ((userCoordinatesInput.length != 2) || (
                    !(userCoordinatesInput[0].matches("^[1-9]\\d*$") &&
                            userCoordinatesInput[1].matches("^[1-9]\\d*$"))
                    )) {
                System.out.println("Некорректная команда, введите две координаты");
                continue;
            }

            xCoordinate = Integer.parseInt(userCoordinatesInput[0]) - 1;
            yCoordinate = Integer.parseInt(userCoordinatesInput[1]) - 1;

            String moveErrorMessage = playingField.makeMoveOnPosition(xCoordinate, yCoordinate);
            if (!SUCCESSFUL_FUNCTION_COMPLETION.equals(moveErrorMessage)) {
                System.out.println(moveErrorMessage);
            }

            if (!playingField.checkPossibilityOfMove()) {
                playingField.changeTurn();
                if (!playingField.checkPossibilityOfMove()) {
                    String gameResult;
                    if (playingField.getGameScore()[0] > playingField.getGameScore()[1]) {
                        gameResult = "Победили Черные";
                    } else if (playingField.getGameScore()[0] < playingField.getGameScore()[1]) {
                        gameResult = "Победили Белые";
                    } else {
                        gameResult = "Ничья";
                    }
                    System.out.printf("Игра окончена!\n%s со счетом %d : %d\nЧтобы начать новую игру, напишите start. Чтобы выйти из игры, напишите quit",
                            gameResult, playingField.getGameScore()[0], playingField.getGameScore()[1]);
                    break;
                }
                System.out.printf("У %s не быт возможного хода, ход переходит к другому игроку", playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "белых\n" : "черных\n");
            }

        }
    }
}
