package io.srikanth.ProfileManager.Codeforces;

import org.springframework.data.cassandra.repository.CassandraRepository;

public interface ContestRepository extends CassandraRepository<Contest, String>{
    
}
