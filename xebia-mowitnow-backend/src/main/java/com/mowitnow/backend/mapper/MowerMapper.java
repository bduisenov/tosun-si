package com.mowitnow.backend.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.mowitnow.backend.constant.MowitnowConstant;
import com.mowitnow.backend.domain.Mower;
import com.mowitnow.backend.domain.Position;
import com.mowitnow.backend.domain.type.Direction;

/**
 * Mapper that allows to transform object that concerns {@link Mower}.
 * 
 * @author Mazlum TOSUN
 */
public enum MowerMapper {

  // Single instance.
  INSTANCE;

  // ----------------------------------------------
  // Public methods
  // ----------------------------------------------

  /**
   * Allows to transform the given applications parameters to {@link Mower} list. These parameters
   * concerns mower number, directions and positions.
   * 
   * @param directionsParams directions parameters
   * @param positionParams position parameters
   * @return {@link Mower} mower list
   */
  public List<Mower> paramsToMowers(final String directionsParams, final String positionParams) {

    // Gets mower number. This number corresponds to directions or positions number.
    final int mowerNumber = directionsParams.split(MowitnowConstant.MOWERS_SEPARATOR).length;

    // Build and gets mower builders by mower number.
    final List<Mower.Builder> mowers = IntStream.range(0, mowerNumber)
        .mapToObj(n -> new Mower.Builder(n)).collect(Collectors.toList());

    // Gets mower directions by directions that given by application parameters.
    final List<List<Direction>> groupedDirections =
        DirectionMapper.INSTANCE.paramsToDirection(directionsParams);

    // Gets mower positions by positions that given by application parameters.
    final List<Position> positions = PositionMapper.INSTANCE.paramsToPositions(positionParams);

    // Directions and positions are in same order to mower. We add each directions/positions to
    // appropriate mower.
    mowers.forEach(m -> m.directions(groupedDirections.get(m.build().getId()))
        .position(positions.get(m.build().getId())));

    // Returns result mower list with directions and positions. Calls stream mapper with build
    // method of mower.Builder object, in order to return mower object.
    return mowers.stream().map(Mower.Builder::build).collect(Collectors.toList());
  }
}