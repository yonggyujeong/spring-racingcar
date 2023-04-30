package racingcar.domain;

import racingcar.domain.dto.RacingCarDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class RacingCars {
    private final List<RacingCar> racingCars;

    public RacingCars(List<RacingCar> racingCars) {
        this.racingCars = racingCars;
    }

    public  RacingCars(String names) {
        this.racingCars = convertNamesToRacingCars(names);
    }

    public int size() {
        return racingCars.size();
    }

    public RacingCar get(int index) {
        return racingCars.get(index);
    }

    private List<RacingCar> convertNamesToRacingCars(String names) {
        return Arrays.stream(this.convertNamesToArray(names))
                .map(it -> new RacingCar(it.trim()))
                .collect(Collectors.toList());
    }

    private String[] convertNamesToArray(String names) {
        return names.split(",");
    }

    public void playRound() {
        Random random = new Random();
        for (RacingCar racingCar : racingCars) {
            int randomNumber = random.nextInt(10);
            racingCar.move(randomNumber);
        }
    }

    public List<String> findWinners() {
        int maxPosition = 0;
        List<String> winners = new ArrayList<>();
        for (RacingCar racingCar : racingCars) {
            if (racingCar.getPosition() > maxPosition) {
                maxPosition = racingCar.getPosition();
                winners.clear();
            }
            if (racingCar.getPosition() >= maxPosition) {
                winners.add(racingCar.getName());
            }
        }

        return winners;
    }

    public List<RacingCarDto> getRacingCarDtos() {
        return racingCars.stream()
                .map(RacingCarDto::from)
                .collect(Collectors.toList());
    }

}