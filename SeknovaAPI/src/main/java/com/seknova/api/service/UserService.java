package com.seknova.api.service;

import com.seknova.api.dto.ApiResponse;
import com.seknova.api.dto.UserInformationRequest;
import com.seknova.api.entity.UserAuth;
import com.seknova.api.entity.UserInformation;
import com.seknova.api.repository.UserAuthRepository;
import com.seknova.api.repository.UserInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserAuthRepository userAuthRepository;
    private final UserInformationRepository userInformationRepository;
    
    public ApiResponse<?> setUserInformation(Long userId, UserInformationRequest request) {
        try {
            UserInformation userInfo = userInformationRepository.findByUserId(userId)
                    .orElse(new UserInformation());
            
            userInfo.setUserId(userId);
            if (request.getFirstName() != null) userInfo.setFirstName(request.getFirstName());
            if (request.getLastName() != null) userInfo.setLastName(request.getLastName());
            if (request.getBirthDay() != null) userInfo.setBirthDay(request.getBirthDay());
            if (request.getPhone() != null) userInfo.setPhone(request.getPhone());
            if (request.getAddress() != null) userInfo.setAddress(request.getAddress());
            if (request.getGender() != null) userInfo.setGender(request.getGender());
            if (request.getHeight() != null) userInfo.setHeight(request.getHeight());
            if (request.getWeight() != null) userInfo.setWeight(request.getWeight());
            if (request.getRace() != null) userInfo.setRace(request.getRace());
            if (request.getLiquor() != null) userInfo.setLiquor(request.getLiquor());
            if (request.getSmoke() != null) userInfo.setSmoke(request.getSmoke());
            if (request.getCheck() != null) userInfo.setCheck(request.getCheck());
            if (request.getPhoneVerified() != null) userInfo.setPhoneVerified(request.getPhoneVerified());
            
            userInformationRepository.save(userInfo);
            return new ApiResponse<>("ok", "User information updated successfully");
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to update user information: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> getUserInformation(Long userId) {
        try {
            UserInformation userInfo = userInformationRepository.findByUserId(userId)
                    .orElseThrow(() -> new Exception("User information not found"));
            
            return new ApiResponse<>("ok", "User information retrieved successfully", userInfo);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to retrieve user information: " + e.getMessage());
        }
    }
    
    public ApiResponse<?> getUserAuth(Long userId) {
        try {
            UserAuth userAuth = userAuthRepository.findById(userId)
                    .orElseThrow(() -> new Exception("User not found"));
            
            return new ApiResponse<>("ok", "User auth retrieved successfully", userAuth);
        } catch (Exception e) {
            return new ApiResponse<>("not", "Failed to retrieve user auth: " + e.getMessage());
        }
    }
}
