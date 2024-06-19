package com.example.demo.service;

import com.example.demo.model.Member;
import com.example.demo.model.Team;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class MemberService {
	
    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired // automatically injects an instance of MemberRepository
    private MemberRepository memberRepository;
    
    @Autowired // automatically injects an instance of TeamRepository
    private TeamRepository teamRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll(); // Fetches and returns all members from the repository
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id); // Fetches and returns a member by its ID, wrapped in an Optional
    }

    public Member createMember(Member member) {
    	 if (member.getTeam() == null || member.getTeam().getId() == null) {
             throw new IllegalArgumentException("Team ID must be provided");
         }
    	 Optional<Team> team = teamRepository.findById(member.getTeam().getId());
    	 if (!team.isPresent()) {
             throw new IllegalArgumentException("Team with ID " + member.getTeam().getId() + " does not exist");
         }
    	
    	 member.setTeam(team.get());
    	 Member savedMember = memberRepository.save(member);
    	 
         logger.info("Member with ID {} and name {} was successfully created.", savedMember.getId(), savedMember.getName());

    	 return savedMember;
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

	public Optional<Member> updatePoints(Long id, Integer points) {
	
		return memberRepository.findById(id).map(member -> {
			member.setPoints(points);
			return memberRepository.save(member);
		});

	}
}