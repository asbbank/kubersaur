package com.darthShana.kubersaur.cli

import com.darthShana.kubersaur.model.Org

class PrepareMicroserviceTest extends spock.lang.Specification {

    def "parse json manifest file"() {
        given:
        def rel = new PrepareMicroservice()
        rel.manifestFile = "./connect-manifest.json"

        when:
        rel.run(new Org(name: "kubersaurus"), null)

        then:
        rel.microservices.size() == 3

    }

}
