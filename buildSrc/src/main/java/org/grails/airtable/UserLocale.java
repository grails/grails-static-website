/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.grails.airtable;

/**
 * @author Sergio del Amo
 * @since 1.0.0
 */
public enum UserLocale {
    AF("af", "Afrikaans", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'af'), 'LLLL') => Woensdag, 8 Junie 2016 20:22"),
    AR_MA("ar-ma", "Moroccan Arabic", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ar-ma'), 'LLLL') => الأربعاء 8 يونيو 2016 20:22"),
    AR_SA("ar-sa","Arabic (Saudi Arabia)","DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ar-sa'), 'LLLL') => الأربعاء ٨ يونيو ٢٠١٦ ٢٠:٢٢"),
    AR_TN("ar-tn", "Tunisian Arabic", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ar-tn'), 'LLLL') => الأربعاء 8 جوان 2016 20:22"),
    AR("ar","Arabic","DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ar'), 'LLLL') => الأربعاء ٨ حزيران يونيو ٢٠١٦ ٢٠:٢٢"),
    AZ("az","Azerbaijani", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'az'), 'LLLL') => Çərşənbə, 8 iyun 2016 20:22"),
    BE("be","Belarusian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'be'), 'LLLL') => серада, 8 чэрвеня 2016 г., 20:22"),
    BG("bg","Bulgarian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'bg'), 'LLLL') => сряда, 8 юни 2016 20:22"),
    BN("bn","Bengali","DATETIME_FORMAT(SET_LOCALE({Date Field}, 'bn'), 'LLLL') => বুধবার, ৮ জুন ২০১৬, রাত ৮:২২ সময়"),
    BO("bo","Tibetan", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'bo'), 'LLLL') => གཟའ་ལྷག་པ་, ༨ ཟླ་བ་དྲུག་པ ༢༠༡༦, མཚན་མོ ༨:༢༢"),
    BR("br","Breton","DATETIME_FORMAT(SET_LOCALE({Date Field}, 'br'), 'LLLL') => Merc'her, 8 a viz Mezheven 2016 8e22 PM"),
    BS("bs", "Bosnian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'bs'), 'LLLL') => srijeda, 8. juni 2016 20:22"),
    CA("ca","Catalan", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ca'), 'LLLL') => dimecres 8 juny 2016 20:22"),
    CS("cs","Czech", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'cs'), 'LLLL') => středa 8. červen 2016 20:22"),
    CV("cv", "Chuvash", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'cv'), 'LLLL') => юнкун, 2016 ҫулхи ҫӗртме уйӑхӗн 8-мӗшӗ, 20:22"),
    CY("cy", "Welsh", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'cy'), 'LLLL') => Dydd Mercher, 8 Mehefin 2016 20:22"),
    DA("da","Danish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'da'), 'LLLL') => onsdag d. 8. juni 2016 20:22"),
    DE_AT("de-at","Austrian German", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'de-at'), 'LLLL') => Dienstag, 12. Jänner 2016 18:48"),
    DE("de","German", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'de'), 'LLLL') => Dienstag, 12. Januar 2016 18:48"),
    EL("el","Modern Greek", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'el'), 'LLLL') => Τετάρτη, 8 Ιουνίου 2016 8:22 ΜΜ"),
    EN_AU("en-au","Australian English", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'en-au'), 'LLL') => 8 June 2016 8:22 PM"),
    EN_CA("en-ca","Canadian English", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'en-ca'), 'LLL') => 8 June, 2016 8:22 PM"),
    EN_GB("en-gb","British English", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'en-gb'), 'LLL') => 8 June 2016 20:22"),
    EN_IE("en-ie","Irish English", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'en-ie'), 'LLL') => June 8, 2016 8:22 PM"),
    EN_NZ("en-nz","New Zealand English", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'en-nz'), 'LLL') => June 8, 2016 8:22 PM"),
    EO("eo","Esperanto", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'eo'), 'LLLL') => Merkredo, la 8-an de junio, 2016 20:22"),
    ES("es","Spanish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'es'), 'LLLL') => Miércoles, 8 de Junio de 2016 20:22"),
    ET("et","Estonian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'et'), 'LLLL') => kolmapäev, 8. juuni 2016 20:22"),
    EU("eu","Euskara (Basque)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'eu'), 'LLLL') => asteazkena, 2016ko ekainaren 8a 20:22"),
    FA("fa","Persian (Farsi)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fa'), 'LLLL') => چهارشنبه، ۸ ژوئن ۲۰۱۶ ۲۰:۲۲"),
    FI("fi","Finnish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fi'), 'LLLL') => keskiviikko, 8. kesäkuuta 2016, klo 20.22"),
    FO("fo","Faroese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fo'), 'LLLL') => mikudagur 8. juni, 2016 20:22"),
    FR_CA("fr-ca","Canadian French", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fr-ca'), 'LLLL') => mercredi 8 juin 2016 20:22"),
    FR_CH("fr-ch","Swiss French", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fr-ch'), 'LLLL') => mercredi 8 juin 2016 20:22"),
    FR("fr","French", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fr'), 'LLLL') => mercredi 8 juin 2016 20:22"),
    FY("fy","Frisian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'fy'), 'LLLL') => woansdei 8 juny 2016 20:22"),
    GL("gl","Galician", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'gl'), 'LLLL') => Mércores 8 Xuño 2016 20:22"),
    HE("he","Hebrew", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'he'), 'LLLL') => רביעי, 8 ביוני 2016 20:22"),
    HI("hi","Hindi", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'hi'), 'LLLL') => बुधवार, ८ जून २०१६, रात ८:२२ बजे"),
    HR("hr","Hrvatski (Croatian)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'hr'), 'LLLL') => 2016. június 8., szerda 20:22"),
    HU("hu","Hungarian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'hu'), 'LLLL') => 2016. június 8., szerda 20:22"),
    HY_AM("hy-am","Armenian (Hayeren)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'hy-am'), 'LLLL') => չորեքշաբթի, 8 հունիսի 2016 թ., 20:22"),
    ID("id","Bahasa Indonesia (Indonesian)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'id'), 'LLLL') => Rabu, 8 Juni 2016 pukul 20.22"),
    IS("is","Icelandic", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'is'), 'LLLL') => miðvikudagur, 8. júní 2016 kl. 20:22"),
    IT("it","Italian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'it'), 'LLLL') => Mercoledì, 8 giugno 2016 20:22"),
    JA("ja","Japanese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ja'), 'LLLL') => 2016年6月8日午後8時22分 水曜日"),
    JV("jv","Boso Jowo (Javanese)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'jv'), 'LLLL') => Rebu, 8 Juni 2016 pukul 20.22"),
    KA("ka","Georgian (Kartuli)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ka'), 'LLLL') => ოთხშაბათი, 8 ივნისი 2016 8:22 PM"),
    KM("km","Khmer", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'km'), 'LLLL') => ពុធ, 8 មិថុនា 2016 20:22"),
    KO("ko","Korean", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ko'), 'LLLL') => 2016년 6월 8일 수요일 오후 8시 22분"),
    LB("lb","Luxembourgish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'lb'), 'LLLL') => Mëttwoch, 8. Juni 2016 20:22 Auer"),
    LT("lt","Lithuanian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'lt'), 'LLLL') => 2016 m. birželis 8 d., trečiadienis, 20:22 val."),
    LV("lv","Latvian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'lv'), 'LLLL') => 2016. gada 8. jūnijs, trešdiena, 20:22"),
    ME("me","Montenegrin", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'me'), 'LLLL') => srijeda, 8. jun 2016 20:22"),
    MK("mk","Macedonian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'mk'), 'LLLL') => среда, 8 јуни 2016 20:22"),
    ML("ml","Malayalam (Kairali)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ml'), 'LLLL') => ബുധനാഴ്ച, 8 ജൂൺ 2016, രാത്രി 8:22 -നു"),
    MR("mr","Marathi", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'mr'), 'LLLL') => बुधवार, ८ जून २०१६, रात्री ८:२२ वाजता"),
    MS("ms","Bahasa Malaysia (Standard Malay)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ms'), 'LLLL') => Rabu, 8 Jun 2016 pukul 20.22"),
    MY("my","Burmese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'my'), 'LLLL') => ဗုဒ္ဓဟူး ၈ ဇွန် ၂၀၁၆ ၂၀:၂၂"),
    NB("nb","Norwegian Bokmål", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'nb'), 'LLLL') => onsdag 8. juni 2016 kl. 20.22"),
    NE("ne","Nepali", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ne'), 'LLLL') => बुधबार, ८ जुन २०१६, रातीको ८:२२ बजे"),
    NL("nl","Dutch", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'nl'), 'LLLL') => woensdag 8 juni 2016 20:22"),
    NN("nn","Norwegian Nynorsk", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'nn'), 'LLLL') => onsdag 8 juni 2016 20:22"),
    PL("pl","Polish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'pl'), 'LLLL') => środa, 8 czerwca 2016 20:22"),
    PT_BR("pt-br","Brazilian Portuguese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'pt-br'), 'LLLL') => Quarta-Feira, 8 de Junho de 2016 às 20:22"),
    PT("pt","Portuguese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'pt'), 'LLLL') => Quarta-Feira, 8 de Junho de 2016 20:22"),
    RO("ro","Romanian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ro'), 'LLLL') => miercuri, 8 iunie 2016 20:22"),
    RU("ru","Russian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ru'), 'LLLL') => среда, 8 июня 2016 г., 20:22"),
    SI("si","Sinhala (Sinhalese)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'si'), 'LLLL') => 2016 ජූනි 8 වැනි බදාදා, ප.ව. 8:22:02"),
    SK("sk","Slovak", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sk'), 'LLLL') => streda 8. jún 2016 20:22"),
    SL("sl","Slovenian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sl'), 'LLLL') => sreda, 8. junij 2016 20:22"),
    SQ("sq","Albanian (Shqip)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sq'), 'LLLL') => E Mërkurë, 8 Qershor 2016 20:22"),
    SR_CYRL("sr-cyrl","Serbian Cyrillic", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sr-cyrl'), 'LLLL') => среда, 8. јун 2016 20:22"),
    SR("sr","Serbian Latin", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sr'), 'LLLL') => sreda, 8. jun 2016 20:22"),
    SV("sv","Swedish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'sv'), 'LLLL') => onsdag 8 juni 2016 20:22"),
    TA("ta","Tamil", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'ta'), 'LLLL') => புதன்கிழமை, 8 ஜூன் 2016, 20:22"),
    TH("th","Thai", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'th'), 'LLLL') => วันพุธที่ 8 มิถุนายน 2016 เวลา 20 นาฬิกา 22 นาที"),
    TL_PH("tl-ph","Tagalog/Filipino", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'tl-ph'), 'LLLL') => Miyerkules, Hunyo 08, 2016 20:22"),
    TR("tr","Turkish", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'tr'), 'LLLL') => Çarşamba, 8 Haziran 2016 20:22"),
    TZL("tzl","Talossan", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'tzl'), 'LLLL') => Márcuri, li 8. Gün dallas 2016 20.22"),
    TZM_LATN("tzm-latn","Morocco Central Atlas Tamaziɣt (Amazigh/Berber) in Latin", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'tzm-latn'), 'LLLL') => akras 8 ywnyw 2016 20:22"),
    TZM("tzm","Morocco Central Atlas Tamaziɣt (Amazigh/Berber)", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'tzm'), 'LLLL') => ⴰⴽⵔⴰⵙ 8 ⵢⵓⵏⵢⵓ 2016 20:22"),
    UK("uk","Ukrainian", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'uk'), 'LLLL') => середа, 8 червня 2016 р., 20:22"),
    UZ("uz","Uzbek", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'uz'), 'LLLL') => 8 июнь 2016, Чоршанба 20:22"),
    VI("vi","Vietnamese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'vi'), 'LLLL') => thứ tư, 8 tháng 6 năm 2016 20:2"),
    ZH_CN("zh-cn","Simplified Chinese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'zh-cn'), 'LLLL') => 2016年6月8日星期三晚上8点22分"),
    ZH_TW("zh-tw","Traditional Chinese", "DATETIME_FORMAT(SET_LOCALE({Date Field}, 'zh-tw'), 'LLLL') => 2016年6月8日星期三晚上8點22分");

    private String modifier;
    private String description;
    private String example;

    UserLocale(String modifier, String description, String example) {
     this.modifier = modifier;
     this.description = description;
     this.example = example;
    }

    public String getDescription() {
     return description;
    }

    public String getExample() {
     return example;
    }

    @Override
    public String toString() {
     return modifier;
    }
}
