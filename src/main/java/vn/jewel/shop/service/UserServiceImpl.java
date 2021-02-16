package vn.jewel.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.jewel.shop.dto.UserRegistrationDto;
import vn.jewel.shop.exception.UserNotEnableException;
import vn.jewel.shop.model.Role;
import vn.jewel.shop.model.SessionHistory;
import vn.jewel.shop.model.User;
import vn.jewel.shop.repository.SessionHistoryRepository;
import vn.jewel.shop.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import eu.bitwalker.useragentutils.UserAgent;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private SessionHistoryRepository sesionHistoryRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.getUserByUserName(username);
    }

    public User findUserActiveByUsername(String username){
        return userRepository.getUserByUserActiveName(username);
    }

    @Transactional
    @Override
    public User save(UserRegistrationDto registration){
    User user = new User();
    user.setFirstName(registration.getFirstName());
    user.setLastName(registration.getLastName());
    user.setEmail(registration.getEmail());
    user.setPassword(passwordEncoder.encode(registration.getPassword()));
    user.setRoles(Arrays.asList(new Role("ROLE_USER")));
    user.setEnabled(0);
    user.setUsername(registration.getUserName());
    return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //defined phương thức đăng nhập bằng email
        User user = userRepository.findByEmail(s);

        if (user == null){
            throw new UsernameNotFoundException("Tên tài khoản hoặc mật khẩu không đúng.");
        }

        if (user.getEnabled()!=1){
            throw new UserNotEnableException("Tài khoản chưa được kích hoạt.");
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles(),user.getUsername()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles, String username){
        SessionHistory entity=new SessionHistory();
        entity.setUsername(username);
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String userAgent = request.getHeader("user-agent");
        UserAgent ua = UserAgent.parseUserAgentString(userAgent);
        String browserName = ua.getBrowser().toString();
        String vesion = ua.getBrowserVersion().toString();
        //     int id= ua.getId();
        InetAddress ip;
        try {
            ip=InetAddress.getLocalHost();
            String ipa= ip.getHostAddress();
            entity.setIp(ipa);
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
//        String ipp=ip.getHostAddress();
//        System.out.println(browserName+" "+id);
        entity.setName_broswer(browserName);

        sesionHistoryRepository.save(entity);
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
