package com.creffer.models.offer;

import com.creffer.models.offer.enums.OfferAdType;
import com.creffer.models.offer.enums.OfferStatus;
import com.creffer.models.offer.enums.OfferType;
import com.creffer.models.offer.enums.TrafficType;
import com.creffer.models.users.UserModel;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "offers")
public class OfferModel {
    @Column(name = "offer_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int offerId;

    @Column(name = "adv_id")
    private int adv_id;

    @Column(name = "adv_offer_id")
    private int advOfferId;

    @Column(name = "redirect_offer_id")
    private int redirectOfferId;

    @Column(name = "suspended")
    private boolean suspended;

    @Column(name = "anti_fraud")
    private boolean antiFraud;

    @Column(name = "sensitive")
    private boolean sensitive;

    @Column(name = "activate_date")
    private LocalDateTime activateDate;

    @Column(name = "skip_affiliates")
    private Set<UserModel> skipAffiliates;

    @Column(name = "skip_percent")
    private float skipPercent;

    @Column(name = "offer_name")
    private String offerName;

    @Column(name = "description")
    private String description;

    @Column(name = "show_usr_flow_msg")
    private boolean showUsrFlowMsg;

    @Column(name = "show_caps_msg")
    private boolean showCapsMsg;

    @Column(name = "comment")
    private String comment;

    @Column(name = "tracking_url")
    private String trackingUrl;

    @Column(name = "tracking_url_suffix")
    private String trackingUrlSuffix;

    @Column(name = "preview_url")
    private String previewUrl;

    @Column(name = "approval_required")
    private boolean approvalRequired;

    @Column(name = "offer_status")
    private OfferStatus offerStatus;

    @Column(name = "currency")
    private int currency_id;

    @Column(name = "category")
    private int cat_id;

    @Column(name = "offer_type")
    private OfferType offerType;

    @Column(name = "offer_ad_type")
    private OfferAdType offerAdType;

    @Column(name = "traffic_type")
    private TrafficType trafficType;

    @Column(name = "cookie_lifetime")
    private int cookieLifetime;

    @Column(name = "visible")
    private boolean visible;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offer_goals",joinColumns = @JoinColumn(name = "offer_id"),
    inverseJoinColumns = @JoinColumn(name = "goal_id"))
    private List<GoalModel> goals;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "offer_restrictions",joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "restr_id"))
    private List<TrafficRestrictionModel> restrictions;

}
