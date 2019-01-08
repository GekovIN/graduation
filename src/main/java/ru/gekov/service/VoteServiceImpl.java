package ru.gekov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gekov.model.Restaurant;
import ru.gekov.model.User;
import ru.gekov.model.Vote;
import ru.gekov.repository.RestaurantRepository;
import ru.gekov.repository.UserRepository;
import ru.gekov.repository.VoteRepository;
import ru.gekov.util.exception.VotingTimeIsOutException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static ru.gekov.util.ValidationUtil.*;

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
        return checkNotFoundWithId(voteRepository.findById(id).orElse(null), id);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return checkNotFoundWithIdAndDate(voteRepository.findByUserIdAndDate(userId, date).orElse(null), userId, date);
    }

    @Override
    @Transactional
    public Vote save(LocalDateTime dateTime, int userId, int restaurantId) {
        Optional<Vote> voteOptional = voteRepository.findByUserIdAndDate(userId, dateTime.toLocalDate());
        if (voteOptional.isPresent()
                && dateTime.toLocalTime().isBefore(VOTE_END_TIME)) {
            return initLazyObj(update(voteOptional.get(), restaurantId));
        } else if (!voteOptional.isPresent()) {
            return initLazyObj(create(dateTime.toLocalDate(), userId, restaurantId));
        } else {
            throw new VotingTimeIsOutException("Vote time: " + VOTE_END_TIME + " is over.");
        }
    }

    //init lazy restaurant and user after saving
    private Vote initLazyObj(Vote vote) {
        vote.getRestaurant().toString();
        vote.getUser().toString();
        return vote;
    }

    private Vote update(Vote vote, int restaurantId) {
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);
    }

    private Vote create(LocalDate date, int userId, int restaurantId) {
        User user = userRepository.getOne(userId);
        Restaurant restaurant = restaurantRepository.getOne(restaurantId);
        return voteRepository.save(new Vote(date, user, restaurant));
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(voteRepository.delete(id) != 0, id);
    }

}
