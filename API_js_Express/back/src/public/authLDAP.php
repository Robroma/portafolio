<?php
header('Access-Control-Allow-Origin: *');
ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);


$ldaprdn = $argv[1];
$ldappass = $argv[2];


$ldapuri = "10.1.1.200"; // your ldap-uri
$ldapport = "389";
$binddn = "uid=$ldaprdn,ou=users,ou=inf,dc=escoladeltreball,dc=org"; //Cal que mireu les vostres "ou" amb la comanda ldapsearch -x -b "dc=escoladeltreball,dc=org" -H ldap://10.1.1.200 | grep usuari


$ldapconn = ldap_connect($ldapuri, $ldapport) or die("Could not connect to LDAP server.");
ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);
ldap_set_option($ldapconn, LDAP_OPT_REFERRALS, 0);



if ($ldapconn) {
    $ldapbind = ldap_bind($ldapconn, $binddn, $ldappass);

    if ($ldapbind) {
        ldap_set_option($ldapconn, LDAP_OPT_PROTOCOL_VERSION, 3);

        $data = ldap_search($ldapconn, "dc=escoladeltreball,dc=org", "(|(uid=$ldaprdn*))");
        $entries = ldap_get_entries($ldapconn, $data);
        if ($entries["count"] > 0) {
            $description = $entries[0]["description"][0];
            $displayName = $entries[0]["sn"][0];
            echo "Description: " . $description . "<br>";
            echo "Display Name: " . $displayName . "<br>";
        } else {
            echo "No se encontraron resultados.";
        }
    } else {
        echo "LDAP bind failed...";
    }
}


ldap_close($ldapconn);

?>