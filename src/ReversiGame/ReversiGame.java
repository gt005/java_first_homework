package ReversiGame;

import java.util.Objects;
import java.util.Scanner;

import static ReversiGame.Constants.*;

/**
 * Управляет ходом игры, взаимодействуя с пользователем, взаимодействует с классами Player и ReversiBoard
 */
public class ReversiGame {

    private static final Scanner scanner = new Scanner(System.in);
    private boolean gameMode;  // Игрок - игрок или компьютер - игрок
    private boolean computerComplexity;

    /**
     * Отслеживает начало новых игр и выход из игры. При первом запуске печатает правила.
     */
    public void startGame() {

        PlayingField playingField = new PlayingField();

        System.out.println(GAME_RULES);
        String userCommand;

        boolean rightCommandFlag = false;
        while (!rightCommandFlag) {
            userCommand = scanner.nextLine();

            if (userCommand.matches("^[1-9]\\d*$")) {
                switch (Integer.parseInt(userCommand)) {
                    case 1:
                        gameMode = PLAYER_COMPUTER_GAME_MODE;
                        rightCommandFlag = true;
                        computerComplexity = EASY_COMPUTER_COMPLEXITY;
                        break;
                    case 2:
                        gameMode = PLAYER_COMPUTER_GAME_MODE;
                        rightCommandFlag = true;
                        computerComplexity = HARD_COMPUTER_COMPLEXITY;
                        break;
                    case 3:
                        gameMode = PLAYER_PLAYER_GAME_MODE;
                        rightCommandFlag = true;
                        break;
                    default:
                        System.out.println("Введено неверное число\n");
                        System.out.println(GAME_RULES);
                        break;
                }
            } else {
                System.out.println("Введено неверное число\n");
                System.out.println(GAME_RULES);
            }
        }

        System.out.println("Чтобы сделать ход в игре, напишите два целых числа: номер столбца и номер строчки хода соответственно.\nЧтобы начать новую игру, напишите start. Чтобы выйти из игры, напишите quit\n");

        while (true) {
            userCommand = scanner.nextLine();
            switch (userCommand) {
                case "start":
                    playingField.createNewGame();
                    communicateWithUser(playingField);
                    break;
                case "quit":
                    return;
                default:
                    System.out.println("Неизвестная команда, Чтобы начать новую игру, напишите start. Чтобы выйти из игры, напишите quit\n");
                    break;
            }
        }
    }

    private void communicateWithUser(PlayingField playingField) {
        int xCoordinate;
        int yCoordinate;
        // массив из двух координат
        String[] userCoordinatesInput;

        while (true) {
            // Информация о партии
            System.out.printf("Счет:\nЧерные: %d\nБелые: %d\n\nХодят %s\n",
                    playingField.getGameScore()[0],
                    playingField.getGameScore()[1],
                    playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "черные\n" : "белые\n"
            );
            System.out.println(playingField.renderPlayingFieldIntoString());

            // Валидация координат хода, которые ввел пользователь
            if (this.gameMode == PLAYER_COMPUTER_GAME_MODE && playingField.getColorOfMove() == REVERSI_WHITE_TURN) {
                String computerMoveResult = playingField.calculateAndMakeComputerMove(this.gameMode);
//                if (!SUCCESSFUL_FUNCTION_COMPLETION.equals(computerMoveResult)) {
//                    System.out.println(computerMoveResult);
//                    playingField.changeTurn();
//                }
                if (isGameEnded(playingField)) {
                    System.out.println(playingField.renderPlayingFieldIntoString());
                    return;
                }
                continue;
            }
            userCoordinatesInput = scanner.nextLine().split(" ");

            // Проверка на два целых числа
            if ((userCoordinatesInput.length != 2) || (
                    !(userCoordinatesInput[0].matches("^[1-9]\\d*$") &&
                            userCoordinatesInput[1].matches("^[1-9]\\d*$"))
                    )) {
                System.out.println("Некорректная команда, введите две координаты\n");
                continue;
            }

            xCoordinate = Integer.parseInt(userCoordinatesInput[0]) - 1;
            yCoordinate = Integer.parseInt(userCoordinatesInput[1]) - 1;

            String moveErrorMessage = playingField.makeMoveOnPosition(xCoordinate, yCoordinate);
            if (!SUCCESSFUL_FUNCTION_COMPLETION.equals(moveErrorMessage)) {
                System.out.println(moveErrorMessage);
            }

            if (isGameEnded(playingField)) {
                System.out.println(playingField.renderPlayingFieldIntoString());
                return;
            }

        }
    }

    private boolean isGameEnded(PlayingField playingField) {
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
                System.out.printf("Игра окончена!\n%s со счетом %d : %d\nЧтобы начать новую игру, напишите start. Чтобы выйти из игры, напишите quit\n",
                        gameResult, playingField.getGameScore()[0], playingField.getGameScore()[1]);
                return true;
            }
            System.out.printf("У %s небыло возможного хода, ход переходит к другому игроку\n", playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "белых\n" : "черных\n");
        }
        return false;
    }
}
