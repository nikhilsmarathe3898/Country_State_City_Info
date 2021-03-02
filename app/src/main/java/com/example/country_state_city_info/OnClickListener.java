package com.example.country_state_city_info;

/*      THis Interface is used for do some click event on static data

1. hamara data API se aa raha he
2. uski click event pe hame click kiya hua data piche ki activity pe leke aana he
3. ya fir APi ke click ka data kisi bhi tarha handle karna he
4. to interface ka use karna he
5. and isko Adapter me use kar liya he
6. waha jaa ke aap dekh sakte ho..
7. T ka matlab Generics. = hume pata nahi ki konsi type ka data aane wala he
    , to wo koi bhi type ko accept karne ke liye lagana hota he..
8. usme position or mode dena hota he
        position ka USE, jo data aana he uski position fetch karne ke liye.
        mode ka USE, specially insert - update and delete ke liye karna hota he.
9. Aapter me onBindViewHolder me holder.METHOD_NAME se hi call karva ke uski Click event wahi pe set kar sakte he.
10. For More Information Go on The Google and Search Interface with JSON in JAVA.

* */

public interface OnClickListener<T> {
    void OnClick(T current, int position, String mode);
}
