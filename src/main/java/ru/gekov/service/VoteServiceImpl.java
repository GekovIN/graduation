package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gekov.model.Restaurant;
import ru.gekov.model.User;
import ru.gekov.model.Vote;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.repository.UserRepository;
import ru.gekov.repository.VoteRepository;
import ru.gekov.util.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    private static final LocalTime VOTE_END_TIME = LocalTime.of(11, 0, 0);

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Vote> getAll() {
        return voteRepository.findAll();
    }

    @Override
    public Vote get(int id) {
        return voteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Vote> getByDate(LocalDate date) {
        return voteRepository.findByDate(date);
    }

    @Override
    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        return voteRepository.findByRestaurantIdAndDate(restaurantId, date);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return voteRepository.findByUserIdAndDate(userId, date).orElse(null);
    }

    @Override
    public Vote save(LocalDateTime dateTime, int userId, int restaurantId) {
        Optional<Vote> voteOptional = voteRepository.findByUserIdAndDate(userId, dateTime.toLocalDate());
        if (voteOptional.isPresent()
                && dateTime.toLocalTime().isBefore(VOTE_END_TIME)
                && dateTime.toLocalDate().equals(LocalDate.now())) {
            return update(voteOptional.get(), restaurantId);
        } else if (!voteOptional.isPresent()) {
            return create(dateTime.toLocalDate(), userId, restaurantId);
        } else {
            throw new VotingTimeIsOutException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    private Vote update(Vote vote, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    private Vote create(LocalDate date, int userId, int restaurantId) {
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        Vote vote = new Vote(date, user, restaurant);
        return voteRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return voteRepository.delete(id) != 0;
    }

    @Override
    public Long countVotes(LocalDate date, int restaurantId) {
        return voteRepository.countAllByRestaurantIdAndDate(restaurantId, date);
    }
}
