package com.example.androidfirebase.utils;

import android.graphics.drawable.Drawable;

import com.example.androidfirebase.R;

public class Constants {
    public static int GOOGLE_SIGN_IN = 1;
    public static String[] hangedMan = {"anap", "anar", "anio", "anoa", "arna", "linia", "inri", "ioni", "naia",
            "naipe", "nana",
            "xaval", "napa", "niar", "nina", "nipo", "noia", "nora", "nori", "orni", "pana", "pian", "poni", "prono",
            "rani",
            "ainar", "arnar", "arran", "irona", "napar", "napia", "ninot", "norai", "orina", "adornar", "panna",
            "paona", "piano",
            "pinar", "pinna", "piran", "prona", "airina", "annona", "apanar", "arniar", "inopia", "ionona", "irania",
            "ironia",
            "ennar", "narrar", "nina", "nina", "nipona", "opinar", "opinio", "orinar", "piorna", "pirona", "raonar",
            "ruinar",
            "aiorina", "arponar", "arranar", "arriana", "non-non", "panarra", "papaina", "pariona", "porrona",
            "propina", "annonari",
            "irania", "opianina", "paranoia", "propinar", "aparionar", "paosar-se", "aporrinar", "ninot", "nona",
            "dolenta",
            "aparionar", "aporrinar", "opinar", "paranoia", "pariona", "piorna", "pirona", "propina", "propinar"};

    public static int[] hangedManTries = {R.drawable.h1, R.drawable.h2, R.drawable.h3, R.drawable.h4, R.drawable.h5,
            R.drawable.h6, R.drawable.h7};

    public static int[] drawablesParaulogics = {R.drawable.p1, R.drawable.p2, R.drawable.p3, R.drawable.p4,
            R.drawable.p5, R.drawable.p6};
    public static final String[][] paraulogics = {{"anap", "anar", "anió", "anoa", "arna", "línia", "inri", "ioni",
            "naia", "naipe", "nana",
            "xaval", "napa", "niar", "nina", "nipó", "noia", "nora", "nori", "orni", "pana", "pian", "poni", "prono",
            "raní",
            "ainar", "arnar", "arran", "irona", "napar", "napia", "ninot", "norai", "orina", "adornar", "panna",
            "paona", "piano",
            "pinar", "pinna", "piran", "prona", "airina", "annona", "apanar", "arniar", "inòpia", "ionona", "iranià",
            "ironia",
            "ennar", "narrar", "nina", "nina", "nipona", "opinar", "opinió", "orinar", "piorna", "pirona", "raonar",
            "ruïnar",
            "aiorina", "arponar", "arranar", "arriana", "non-non", "panarra", "papaïna", "pariona", "porrona",
            "propina", "annonari",
            "iranià", "opianina", "paranoia", "propinar", "aparionar", "posar-se", "aporrinar", "ninot", "nona",
            "dolenta",
            "aparionar", "aporrinar", "opinar", "paranoia", "pariona", "piorna", "pirona", "propina", "propinar"},
            {"abans", "aede", "alada", "llogaret", "aldol", "aldosa", "ajada", "alotada", "asalada", "assolellada",
                    "atotada", "doneu", "dada",
                    "dal", "dalet", "sega", "dalla", "baixada", "del", "amunt", "data", "dea", "dédal", "deessa",
                    "del", "delada", "delat",
                    "allà", "delta", "des", "guardada", "desè", "desolada", "desolat", "disset", "sota", "detall",
                    "detallada", "detallat",
                    "dodo", "doella", "dol", "raig", "dolla", "dolós", "dolosa", "dos", "dosè", "dosset", "dot",
                    "dotal", "edat", "edetà",
                    "elodea", "estada", "estadal", "desbrossament", "estelada", "estesa", "estesa", "ladella",
                    "lledoner", "beurada", "losada",
                    "oda", "ollada", "agosarada", "sad", "sachuelo", "sacia", "salada", "saldo", "saltada",
                    "assolellada", "seda", "sedal",
                    "garbell", "sedós", "sedosa", "soda", "sodada", "sodat", "paviment", "soldada", "soldat",
                    "solitud", "assolellada", "solod",
                    "bajolada", "tallada", "taxada", "telleda", "tesellada", "toledà", "tossada", "tsade"},
            {"acampta", "acampat", "acimat", "acme", "acmita", "aimia", "estima", "amagat", "amè", "àmic", "amic",
                    "Amícia", "amimia", "amit", "amistat", "ampit", "atemàtic", "atemàtica", "atemptat", "cama",
                    "camacita", "camado", "llitera", "camí", "llitera", "camp", "campa", "catatímia", "cim", "cim",
                    "cimaci", "cimera", "ema", "emaciat", "emètic", "emètica", "emmacat", "empacat", "empait", "empat"
                    , "empatia", "empàtic", "empàtica", "empiema", "empet", "epítema", "ètim", "imant", "imamat",
                    "impacte", "impia", "impietat", "ítem", "mac", "maca", "test", "macip", "mai", "maia",
                    "mam", "mare", "mamei", "mamia", "mapa", "mat", "mata", "matacà", "matapan", "mat", "matem" +
                    "àtic", "matemàtica", "matí", "matitat", "meat", "mec", "meca", "meitat", "mem", "met", "meta" +
                    "metamatemàtica", "metatètic", "metatètica", "metec", "meva", "poc", "micaci", "micacia",
                    "micket", "mieta", "mim", "mimètic", "mimètica", "mímic", "mímica", "meitat", "mite", "mític", "m" +
                    "ítica", "pam", "pampa", "pampeta", "patapam", "picamá", "pim-pam", "pitam", "pítima", "tam-tam",
                    "tampa", "tatami", "tem", "tema", "temàtic", "temàtica", "tiama", "tim", "tímic", "tímica", "timpà"
            }, {"aga", "àgam", "agama", "agamí", "agamia", "bestiar", "àgata", "agatis", "agitanat", "agitat"
            , "agminat", "agnat", "àgnata", "agnatia", "amagat", "amiga", "angítis", "angina", "antiga",
            "antigàs", "assagista", "assaig", "assignat", "gag", "gai", "gaia", "gaiato", "gaiata", "gaig", "gaita",
            "gam", "gamma", "gammat", "fam", "guanya", "ganga", "gansa", "ganta", "gas", "gasa", "gasa", "gat", "gata"
            , "gates", "giga", "gimnàs", "gimnasta", "gin", "git", "gita", "gitam", "gitana", "acotat", "ígne",
            "ígnia", "insígnia", "mag", "maga", "magí", "màgia", "magma", "magna", "magnànim", "magnànima",
            "magnanimitat", "magnat", "maig", "manganat", "manganina", "màniga", "massatgista", "mitjà", "mediomatita"
            , "mitigant", "míting", "nagana", "ngai", "cap-nang", "ning-ning", "nissaga", "saga", "llard", "sagita",
            "sagiat", "sagnant", "sagnat", "sagnia", "sajo", "saiga", "sang", "sagnia", "siamang", "sigma", "signant"
            , "signat", "sinangi", "singamia", "sintagma", "taigá", "tanga"}, {"oportú", "ort", "ortòtrop", "os",
            "port", "porto", "post", "postor", "pot", "potó", "potos", "postrós", "potto", "protó", "protutor",
            "puput", "rost", "rot", "rotor", "ruptor", "cenyit", "sort", "afortunat", "sotrac", "sots", "suport",
            "surt", "top", "tor", "toro", "torpor", "torr", "torró", "tors", "torçat", "coca", "tortuós", "torus",
            "tos", "tossut", "tost", "tostorro", "tot", "tou", "tro", "trop", "tros", "trot", "trotó", "trust", "tur"
            , "turó", "tururut", "tossor", "cop", "tut", "tutor", "tutú"}, {
            "acrofòbia", "afàcia", "afí", "afició", "aforar", "africà", "africació", "africar", "baf", "bàfia",
            "bafor", "bifi", "bífia", "bifòbia", "bífora", "bocafí", "bòfia", "caf", "caroficia", "cof", "cofa",
            "cofar", "cofí", "còfia", "satisfet", "satisfeta", "escorça", "en off", "fabàcia", "fabaria", "fabrià",
            "fàbrica", "fabricació", "fabricar", "facció", "fai", "faió", "far", "faraó", "emplenar", "fàrfara",
            "faria", "faró", "farro", "fiar", "fibra", "fic", "ficar", "ficció", "fira", "ferir", "fòbia", "foc",
            "foca", "fofa", "fofo", "foia", "for", "fora", "foraria", "forbir", "forco", "forca", "forcar", "forcó",
            "folra", "folre", "fra", "frac", "fracció", "fraró", "fricció", "ofici", "oficiar", "òrfic", "òrfica",
            "orifici", "rafi", "ràfia", "rafió", "rarificació", "rarificar", "rifa", "rifar"
    }
    };

}
