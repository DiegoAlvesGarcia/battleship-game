package br.fatecsp.engsw3.battleship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.fatecsp.engsw3.battleship.model.User;
import br.fatecsp.engsw3.battleship.repository.UserRepository;

@Transactional
@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userRepository.findByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}
		
}
