package com.digipay.cardmanagement.service;

import com.digipay.cardmanagement.dto.CreateCardDto;
import com.digipay.cardmanagement.dto.UserDto;
import com.digipay.cardmanagement.entity.Card;
import com.digipay.cardmanagement.entity.User;
import com.digipay.cardmanagement.exceptions.ServiceException;
import com.digipay.cardmanagement.exceptions.error.ErrorCode;
import com.digipay.cardmanagement.exceptions.error.FieldErrorDTO;
import com.digipay.cardmanagement.mapper.CardMapper;
import com.digipay.cardmanagement.mapper.UserMapper;
import com.digipay.cardmanagement.repository.UserRepository;
import com.digipay.cardmanagement.web.rest.UserController;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
  private final UserMapper userMapper;
  private final UserRepository userRepository;
  private final CardMapper cardMapper;

  public UserService(UserMapper userMapper, UserRepository userRepository, CardMapper cardMapper) {
    this.userMapper = userMapper;
    this.userRepository = userRepository;
    this.cardMapper = cardMapper;
  }

  public UserDto createUser(UserDto userDto) throws ServiceException {
    logger.info(" Request to save user base on {}", userDto);
    User user = userMapper.userDtoToUser(userDto);
    saveUser(user, user.getCards(), "this user already exist");
    return userMapper.userToUserDto(user);
  }

  private void saveUser(User user, Set<Card> cards, String message) throws ServiceException {
    if (cards == null) return;
    cards.stream().map(card -> card.setUser(user)).collect(Collectors.toSet());
    user.setCards(cards);
    try {
      userRepository.save(user);
    } catch (DataIntegrityViolationException ex) {
      throw new ServiceException(
          new FieldErrorDTO()
              .setErrorDescription(message)
              .setErrorCode(String.valueOf(ErrorCode.DUPLICATE_DATA.getCode())));
    }
  }

  public UserDto addCardToUser(CreateCardDto createCardDto) throws ServiceException {
    Optional<User> foundedUser = getById(createCardDto.getUserId());
    Set<Card> cards;
    if (!foundedUser.isPresent()) {
      throw new ServiceException(
          new FieldErrorDTO()
              .setErrorDescription(ErrorCode.USER_NOT_EXIST.getMessage())
              .setErrorCode(String.valueOf(ErrorCode.USER_NOT_EXIST.getCode())));
    }
    User user = foundedUser.get();
    cards = user.getCards();
    cards.addAll(cardMapper.cardDtosToCards(createCardDto.getCardDtos()));
    saveUser(user, cards, "this card number already exist");
    return userMapper.userToUserDto(user);
  }

  public Optional<User> getById(Long userId) {
    return userRepository.findById(userId);
  }
}
