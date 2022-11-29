package ReversiGame;

import java.util.Scanner;

import static ReversiGame.Constants.*;

/**
 * Управляет ходом игры, взаимодействуя с пользователем
 */
public class ReversiGame {

    private static final Scanner scanner = new Scanner(System.in);
    private boolean gameMode;  // Игрок - игрок или компьютер - игрок
    private boolean computerComplexity;
    private PlayingField playingField;

    /**
     * Отслеживает начало новых игр и выход из игры. При первом запуске печатает правила.
     */
    public void startGame() {
        this.playingField = new PlayingField();

        System.out.println(GAME_RULES);

        while (true) {

            if (isRequiredToStartGame()) {
                System.out.println(POSSIBLE_GAME_MODES_SELECTION_DESCRIPTION);
                getGameModeFromUser();

                playingField.createNewGame();
                communicateWithUser();
            } else {
                break;
            }
        }
    }

    /**
     * Выполняет ходы игроков по координатам. Посылает команды на ход в PlayingField.
     */
    private void communicateWithUser() {
        int xCoordinate;
        int yCoordinate;
        // массив из двух координат
        String[] userCoordinatesInput;

        while (true) {
            // Информация о партии
            System.out.printf("Чтобы отменить ход, напишите \"cancel\" вместо координат хода.\nСчет:\nЧерные: %d\nБелые: %d\n\nХодят %s\n",
                    playingField.getGameScore()[0],
                    playingField.getGameScore()[1],
                    playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "черные\n" : "белые\n"
            );
            System.out.println(playingField.renderPlayingFieldIntoString());

            // Валидация координат хода, которые ввел пользователь
            if (this.gameMode == PLAYER_COMPUTER_GAME_MODE && playingField.getColorOfMove() == REVERSI_WHITE_TURN) {
                playingField.calculateAndMakeComputerMove(computerComplexity);

                if (isGameEnded()) {
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

                if (userCoordinatesInput.length == 1 && "cancel".equals(userCoordinatesInput[0])) {
                    String errorString = playingField.cancelLastPlayerMove(this.gameMode);

                    if (!SUCCESSFUL_FUNCTION_COMPLETION.equals(errorString)) {
                        System.out.println(errorString);
                    }

                    continue;
                }

                System.out.println("Некорректная команда, введите две координаты\n");
                continue;
            }

            xCoordinate = Integer.parseInt(userCoordinatesInput[0]) - 1;
            yCoordinate = Integer.parseInt(userCoordinatesInput[1]) - 1;

            String moveErrorMessage = playingField.makeMoveOnPosition(xCoordinate, yCoordinate, false);
            if (!SUCCESSFUL_FUNCTION_COMPLETION.equals(moveErrorMessage)) {
                System.out.println(moveErrorMessage);
            }

            if (isGameEnded()) {
                System.out.println(playingField.renderPlayingFieldIntoString());
                return;
            }

        }
    }

    /**
     * Проверяет, есть ли возможные ходы у обеих сторон. Если ход есть только у одной стороны, функция меняет ход,
     * оповещает об этом пользователя и продолжает игры. Если игра закончены, выводит результат партии.
     * @return true, если игры окончена и ни одна сторона не может ходить, иначе false.
     */
    private boolean isGameEnded() {
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
                System.out.printf("Игра окончена!\n%s со счетом %d : %d\n" + USER_REMOTE_GAME_COMMANDS,
                        gameResult, playingField.getGameScore()[0], playingField.getGameScore()[1]);

                playingField.setBlackBestScore(playingField.getGameScore()[0]);
                playingField.setWhiteBestScore(playingField.getGameScore()[1]);
                return true;
            }
            System.out.printf("У %s не было возможного хода, ход переходит к другому игроку\n", playingField.getColorOfMove() == REVERSI_BLACK_TURN ? "белых" : "черных");
        }
        return false;
    }

    /**
     * Ожидает получения информации о запуске новой игры или выхода из нее.
     * @return true, если пользователь требует начать игру, иначе false.
     */
    private boolean isRequiredToStartGame() {
        String userCommand;

        System.out.println("Чтобы сделать ход в игре, напишите два целых числа:" +
                " номер столбца и номер строчки хода соответственно.\n" + USER_REMOTE_GAME_COMMANDS);

        while (true) {
            userCommand = scanner.nextLine();
            switch (userCommand) {
                case "start":
                    return true;
                case "quit":
                    return false;
                case "score":
                    if (playingField.getBlackBestScore() != 0) {
                        System.out.printf("Лучший счет у Черных - %d, у Белых - %d\n" + USER_REMOTE_GAME_COMMANDS,
                                playingField.getBlackBestScore(), playingField.getWhiteBestScore());
                    } else {
                        System.out.println("Еще не было ни одной игры, поэтому лучшего счета нет.\n" + USER_REMOTE_GAME_COMMANDS);
                    }
                    break;
                default:
                    System.out.println(USER_REMOTE_GAME_COMMANDS);
                    break;
            }
        }
    }

    /**
     * Ожидает получения информации о режиме игры и устанавливает этот режим для данного класса.
     */
    private void getGameModeFromUser() {
        String userCommand;

        while (true) {
            userCommand = scanner.nextLine();

            if (userCommand.matches("^[1-9]\\d*$")) {
                switch (Integer.parseInt(userCommand)) {
                    case 1:
                        gameMode = PLAYER_COMPUTER_GAME_MODE;
                        computerComplexity = EASY_COMPUTER_COMPLEXITY;
                        return;
                    case 2:
                        gameMode = PLAYER_COMPUTER_GAME_MODE;
                        computerComplexity = HARD_COMPUTER_COMPLEXITY;
                        return;
                    case 3:
                        gameMode = PLAYER_PLAYER_GAME_MODE;
                        return;
                    default:
                        System.out.println("Введено неверное число\n");
                        System.out.println(POSSIBLE_GAME_MODES_SELECTION_DESCRIPTION);
                        break;
                }
            } else {
                System.out.println("Введено неверное число\n");
                System.out.println(POSSIBLE_GAME_MODES_SELECTION_DESCRIPTION);
            }
        }
    }

}
